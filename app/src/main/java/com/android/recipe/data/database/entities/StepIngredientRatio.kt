package com.android.recipe.data.database.entities

import androidx.room.Entity

@Entity(tableName = "stepWithIngredients", primaryKeys = ["name", "ingredientId"])
data class StepIngredientRatio(
    val name: String,
    val ingredientId: Int
)