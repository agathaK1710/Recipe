package com.android.recipe.data.network.model

import com.google.gson.annotations.SerializedName

data class RandomRecipeListDto(
    @SerializedName("recipes")
    val randomRecipesId: List<RecipeIdDto>
)
