package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NutrientDto(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("amount")
    @Expose
    val amount: Double
)