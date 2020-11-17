package com.example.recipeadviser

import android.os.Parcel
import android.os.Parcelable

class SerializableStep(val recipeId: String?,
                              val number: Int?,
                              val description: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        recipeId = parcel.readString(),
        number = parcel.readInt(),
        description = parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(recipeId)
        if (number != null) {
            parcel.writeInt(number)
        }
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SerializableStep> {
        override fun createFromParcel(parcel: Parcel): SerializableStep {
            return SerializableStep(parcel)
        }

        override fun newArray(size: Int): Array<SerializableStep?> {
            return arrayOfNulls(size)
        }
    }
}