package com.meeweel.materialdesign.ui.picture

sealed class PictureOfTheYesterdayData {
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfTheYesterdayData()
    data class Error(val error: Throwable) : PictureOfTheYesterdayData()
    data class Loading(val progress: Int?) : PictureOfTheYesterdayData()
}
