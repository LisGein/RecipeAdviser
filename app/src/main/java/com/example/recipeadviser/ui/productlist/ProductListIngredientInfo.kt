package com.example.recipeadviser.ui.productlist
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_list_ingredient")
class ProductListIngredientInfo(@PrimaryKey @ColumnInfo(name = "id") val ingredient_id: String,
                                @ColumnInfo(name = "state") val state: Boolean) {
}


