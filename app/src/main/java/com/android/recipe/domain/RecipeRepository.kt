package com.android.recipe.domain

import androidx.lifecycle.LiveData
import com.android.recipe.domain.RecipeInfo

interface RecipeRepository {
    fun getRecipeList(): LiveData<List<RecipeInfo>>
    fun getRecipeInfo(id: Int): LiveData<RecipeInfo>
    fun addRecipe(recipe: RecipeInfo)
    fun removeRecipe(recipe: RecipeInfo)
    fun editRecipe(recipe: RecipeInfo)
    suspend fun loadData()
}