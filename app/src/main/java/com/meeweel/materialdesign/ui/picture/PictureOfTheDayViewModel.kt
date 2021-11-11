package com.meeweel.materialdesign.ui.picture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.materialdesign.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
) :
    ViewModel() {

    fun getData(): LiveData<PictureOfTheDayData> {
        sendServerRequest(1)
        return liveDataForViewToObserve
    }
    fun getYesterdayData(): LiveData<PictureOfTheDayData> {
        sendServerRequest(2)
        return liveDataForViewToObserve
    }
    fun get2DaysAgoData(): LiveData<PictureOfTheDayData> {
        sendServerRequest(3)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(day: Int) {
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            val call = when (day) {
                1 -> retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey)
                2 -> retrofitImpl.getRetrofitImpl().getPictureOfTheYesterday(apiKey)
                3 -> retrofitImpl.getRetrofitImpl().getPictureOfThe2DaysAgo(apiKey)
                else -> retrofitImpl.getRetrofitImpl().getPictureOfThe2DaysAgo(apiKey)
            }
            call.enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            PictureOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
                }
            })
        }
    }
}
