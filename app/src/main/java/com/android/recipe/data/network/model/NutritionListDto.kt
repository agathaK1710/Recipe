package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NutritionListDto(
    @SerializedName("nutrients")
    @Expose
    val nutrients: List<NutrientDto>
)