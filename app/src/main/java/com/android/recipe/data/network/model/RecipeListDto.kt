package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeListDto(
    @SerializedName("results")
    @Expose
    val foods: List<MealDto>?
)