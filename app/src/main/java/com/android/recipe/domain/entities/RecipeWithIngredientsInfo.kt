package com.android.recipe.domain.entities

data class RecipeWithIngredientsInfo(
    val recipe: RecipeInfo,
    val ingredients: List<IngredientInfo>?
)