package com.example.akujuga.view.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dummy(
    val name: String,
    val photo: Int
) : Parcelable