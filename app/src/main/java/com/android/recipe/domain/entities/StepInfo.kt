package com.android.recipe.domain.entities

data class StepInfo(
    val recipeId: Int,
    val description: String?,
    val number: Int?,
    val equipments: String?
)
