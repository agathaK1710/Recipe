package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeDetailDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("healthScore")
    @Expose
    val healthScore: Int,
    @SerializedName("extendedIngredients")
    @Expose
    val extendedIngredients: List<IngredientDto>,
    @SerializedName("readyInMinutes")
    @Expose
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("dishTypes")
    @Expose
    val dishTypes: List<String>,
    @SerializedName("instructions")
    @Expose
    val instructions: String,
    @SerializedName("analyzedInstructions")
    @Expose
    val analyzedInstructions: List<InstructionDto>
)