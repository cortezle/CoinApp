package com.dany.coins.Models

import android.os.Parcel
import android.os.Parcelable

data class Coin
    (
    val name: String = "N/A",
    val country: String= "N/A",
    val value: Double= 0.0,
    val value_us: Double= 0.0,
    val year: Int=0,
    val review: String= "N/A",
    val isAvailable: Boolean=false,
    val img: String= "N/A"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        country = parcel.readString(),
        value = parcel.readDouble(),
        value_us = parcel.readDouble(),
        year = parcel.readInt(),
        review = parcel.readString(),
        isAvailable = parcel.readByte() != 0.toByte(),
        img = parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeDouble(value)
        parcel.writeDouble(value_us)
        parcel.writeInt(year)
        parcel.writeString(review)
        parcel.writeByte(if (isAvailable) 1 else 0)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}