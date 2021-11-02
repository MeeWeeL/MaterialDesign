package com.meeweel.materialdesign.ui.other

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.materialdesign.BuildConfig
import com.meeweel.materialdesign.ui.picture.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitOtherImpl = PODRetrofitOtherImpl()
) :
    ViewModel() {

    fun getMoon(): LiveData<PictureOfTheDayData> {
        sendServerRequest(1)
        return liveDataForViewToObserve
    }
    fun getPlanet(): LiveData<PictureOfTheDayData> {
        sendServerRequest(2)
        return liveDataForViewToObserve
    }
    fun getWeather(): LiveData<PictureOfTheDayData> {
        sendServerRequest(3)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(action: Int) {
        val apiKey: String = BuildConfig.NASA_API_KEY
        val call = when (action) {
            1 -> retrofitImpl.getRetrofitImpl().getMoon(apiKey)
            2 -> retrofitImpl.getRetrofitImpl().getPlanet(apiKey)
            3 -> retrofitImpl.getRetrofitImpl().getWeather(apiKey)
            else -> retrofitImpl.getRetrofitImpl().getMoon(apiKey)
        }
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
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