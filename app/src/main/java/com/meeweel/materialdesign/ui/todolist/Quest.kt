package com.meeweel.materialdesign.ui.todolist

import android.os.Parcelable
import com.meeweel.materialdesign.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quest (
    var list: Int = 1,
    var title: String = "Title",
    var description: String = "Description",
    var image: Int = R.drawable.anig,
    var imageInt: Int = 0
) : Parcelable