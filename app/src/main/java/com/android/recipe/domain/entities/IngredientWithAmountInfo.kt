package com.android.recipe.domain.entities

data class IngredientWithAmountInfo(
    val recipeId: Int,
    val ingredientId: Int,
    val amount: Double,
    val unit: String
)