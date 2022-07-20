package com.android.recipe.data.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailDto(
    val id: Int,
    val title: String,
    @SerializedName("aggregateLikes")
    val likes: Int,
    val image: String,
    val healthScore: Int,
    val extendedIngredients: List<IngredientDto>,
    val readyInMinutes: Int,
    val servings: Int,
    val dishTypes: List<String>,
    val instructions: String,
    val analyzedInstructions: List<InstructionDto>,
    val nutrition: NutritionListDto?
)