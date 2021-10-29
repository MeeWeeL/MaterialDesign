package com.meeweel.materialdesign.ui.other

import com.meeweel.materialdesign.ui.picture.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface OthersApi {

    @GET("planetary/apod")
    fun getMoon(@Query("api_key") apiKey: String): Call<PODServerResponseData>
    @GET("planetary/apod")
    fun getPlanet(@Query("api_key") apiKey: String): Call<PODServerResponseData>
    @GET("planetary/apod")
    fun getWeather(@Query("api_key") apiKey: String): Call<PODServerResponseData>
}