package com.android.recipe.data.database.entities

import androidx.room.Entity

@Entity(tableName = "stepWithIngredients", primaryKeys = ["stepId", "ingredientId"])
data class StepIngredientRatio(
    val stepId: Int,
    val ingredientId: Int
)