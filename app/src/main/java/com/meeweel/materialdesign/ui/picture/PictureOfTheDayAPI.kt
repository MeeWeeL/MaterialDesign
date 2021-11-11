package com.meeweel.materialdesign.ui.picture


import android.provider.ContactsContract
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Year
import java.util.*

interface PictureOfTheDayAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String, @Query("date") yesterday: String = "2021-${(Date().month.toInt()+1).toString()}-${(Date().date.toInt()-1).toString()}"): Call<PODServerResponseData>
    @GET("planetary/apod")
    fun getPictureOfTheYesterday(@Query("api_key") apiKey: String, @Query("date") yesterday: String = "2021-${(Date().month.toInt()+1).toString()}-${(Date().date.toInt()-2).toString()}"): Call<PODServerResponseData>
    @GET("planetary/apod")
    fun getPictureOfThe2DaysAgo(@Query("api_key") apiKey: String, @Query("date") yesterday: String = "2021-${(Date().month.toInt()+1).toString()}-${(Date().date.toInt()-3).toString()}"): Call<PODServerResponseData>
}
