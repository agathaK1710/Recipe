package com.android.recipe.data.network.model

data class RecipeDto(
    val id: Int,
    val image: String,
    val likes: Int,
    val title: String,
    val missedIngredientCount: Int,
    val missedIngredients: List<IngredientDto>,
    val usedIngredientCount: Int,
    val usedIngredients: List<IngredientDto>
)
