package com.android.recipe.domain

data class RecipeInfo(
    val id: Int,
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
