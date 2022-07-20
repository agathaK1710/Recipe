package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("amount")
    @Expose
    val amount: Double,
    @SerializedName("unit")
    @Expose
    val unit: String
)
