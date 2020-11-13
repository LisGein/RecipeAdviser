package com.example.recipeadviser

import android.os.Parcel
import android.os.Parcelable

class SerializableIngredients(val ingredientId: String?,
                              val name: String?,
                              val amount: String?,
                              val type: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            ingredientId = parcel.readString(),
            name = parcel.readString(),
            amount = parcel.readString(),
            type = parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ingredientId)
        parcel.writeString(name)
        parcel.writeString(amount)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SerializableIngredients> {
        override fun createFromParcel(parcel: Parcel): SerializableIngredients {
            return SerializableIngredients(parcel)
        }

        override fun newArray(size: Int): Array<SerializableIngredients?> {
            return arrayOfNulls(size)
        }
    }
}