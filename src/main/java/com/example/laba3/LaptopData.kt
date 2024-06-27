package com.example.laba3

import android.os.Parcel
import android.os.Parcelable

data class LaptopData(val title: String, val RAM: Int, val proc: String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(RAM)
        parcel.writeString(proc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LaptopData> {
        override fun createFromParcel(parcel: Parcel): LaptopData {
            return LaptopData(parcel)
        }

        override fun newArray(size: Int): Array<LaptopData?> {
            return arrayOfNulls(size)
        }
    }

}
