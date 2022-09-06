package com.android.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    val recipeId: Int, //todo id
    val favouriteRecipe: Boolean, //todo extract this information to another table?
    val popularRecipe: Boolean,
    val image: String,
    val title: String,
    val likes: Int,
    val healthScore: Int,
    val readyInMinutes: Int,
    val servings: Int,
    val dishTypes: String,
    val instructions: String,
    val calories: Double,
    val carbs: Double,
    val fat: Double,
    val protein: Double,
    val cuisine: String
)