package com.android.recipe.domain.entities

data class RecipeWithStepsInfo(
    val recipe: RecipeInfo,
    val steps: List<StepInfo>?
)
