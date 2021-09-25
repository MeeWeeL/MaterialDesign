package com.meeweel.materialdesign.ui.picture

sealed class PictureOfThe2DaysAgoData {
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfThe2DaysAgoData()
    data class Error(val error: Throwable) : PictureOfThe2DaysAgoData()
    data class Loading(val progress: Int?) : PictureOfThe2DaysAgoData()
}
