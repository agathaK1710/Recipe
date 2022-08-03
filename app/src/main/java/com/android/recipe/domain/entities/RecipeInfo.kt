package com.android.recipe.domain.entities

data class RecipeInfo(
    val id: Int,
    val favouriteRecipe: Int,
    val title: String,
    val likes: Int,
    val image: String,
    val healthScore: Int,
    val readyInMinutes: Int,
    val servings: Int,
    val dishTypes: String,
    val instructions: String,
    val calories: Double,
    val carbs: Double,
    val fat: Double,
    val protein: Double
)
