package com.android.recipe.data.network.model

import com.google.gson.annotations.SerializedName


data class NutrientDto(
    val name: String,
    val amount: Double,
    @SerializedName("percentOfDailyNeeds")
    val percent: Double
)