package com.android.recipe.presentation

data class Step(
    val description: String,
    val number: Int,
    val equipments: String?,
    val ingredients: String
)