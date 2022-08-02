package com.android.recipe.domain

import androidx.lifecycle.LiveData
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.entities.RecipeWithIngredientsInfo
import com.android.recipe.domain.entities.RecipeWithStepsInfo
import com.android.recipe.domain.entities.StepWithIngredientsInfo

interface RecipeRepository {
    fun getRecipeList(): LiveData<List<RecipeInfo>>
    fun getRecipeInfo(id: Int): LiveData<RecipeInfo>
    suspend fun addRecipe(recipe: RecipeInfo)
    suspend fun removeRecipe(recipe: RecipeInfo)
    fun editRecipe(recipe: RecipeInfo)
    fun getRecipeWithIngredients(id: Int): LiveData<RecipeWithIngredientsInfo>
    fun getRecipeWithSteps(id: Int): LiveData<RecipeWithStepsInfo>
    fun getStepWithIngredients(id: Int): LiveData<StepWithIngredientsInfo>
    suspend fun loadData()
}