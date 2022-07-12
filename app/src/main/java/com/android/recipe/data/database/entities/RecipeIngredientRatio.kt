package com.android.recipe.data.database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredientRatio(
    val recipeId: Int,
    val ingredientId: Int,
    val amount: Int,
    val unit: String
)
