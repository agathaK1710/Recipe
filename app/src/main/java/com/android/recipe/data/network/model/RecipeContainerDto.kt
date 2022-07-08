package com.android.recipe.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe_info")
data class RecipeContainerDto(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("likes")
    @Expose
    val likes: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("missedIngredientCount")
    @Expose
    val missedIngredientCount: Int,
    @SerializedName("missedIngredients")
    @Expose
    val missedIngredients: List<IngredientDto>? = null,
    @SerializedName("usedIngredientCount")
    @Expose
    val usedIngredientCount: Int,
    @SerializedName("usedIngredients")
    @Expose
    val usedIngredients: List<IngredientDto>? = null
)
