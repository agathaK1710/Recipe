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
    val calories: Int,
    val carbs: String,
    val fat: String,
    val protein: String,
    val ingredients: List<IngredientInfo>,
    val steps: List<StepInfo>
)
