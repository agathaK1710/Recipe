package com.android.recipe.data.database.entities

import androidx.room.Entity

@Entity(tableName = "recipeWithIngredients", primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredientRatio(
    val recipeId: Int,
    val ingredientId: Int,
    val amount: Int,
    val unit: String
)
