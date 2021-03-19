package com.android.ucast.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customerss(
    var gambar : Int,
    var nama:String,
    var noHp:String,
    var value:Int?=0
):Parcelable