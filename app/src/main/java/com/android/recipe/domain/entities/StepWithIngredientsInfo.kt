package com.android.recipe.domain.entities

data class StepWithIngredientsInfo(
    val step: StepInfo,
    val ingredients: List<IngredientInfo>
)