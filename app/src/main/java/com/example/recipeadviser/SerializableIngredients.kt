package com.example.recipeadviser

import android.os.Parcel
import android.os.Parcelable
import com.example.recipeadviser.localdatabase.ingredients.IngredientData

fun convertToSerializableIngredients(ingr: IngredientData) : SerializableIngredients
{
    return SerializableIngredients(ingr.ingredient_id, ingr.name, ingr.amount, ingr.measure, ingr.type)
}

class SerializableIngredients(val ingredientId: String?,
                              val name: String?,
                              var amount: Double?,
                              val measure: String?,
                              val type: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            ingredientId = parcel.readString(),
            name = parcel.readString(),
            amount = parcel.readDouble(),
            measure = parcel.readString(),
            type = parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ingredientId)
        parcel.writeString(name)
        if (amount != null) {
            parcel.writeDouble(amount!!)
        }
        parcel.writeString(measure)
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

    fun addAmount(i_amount: Double){
        amount = amount?.plus(i_amount)
    }

    override fun toString(): String {
        val intAmount = amount?.toInt()
        val strAmount = if (amount == intAmount?.toDouble()) {
            intAmount.toString()
        } else {
            amount.toString()
        }

        return "$name $strAmount $measure"
    }
}