package com.android.recipe.domain

import androidx.lifecycle.LiveData
import com.android.recipe.domain.entities.*

interface RecipeRepository {
    fun getRecipesList(cuisine: String): LiveData<List<RecipeInfo>>
    suspend fun getRecipeInfo(id: Int): RecipeInfo
    suspend fun getIngredientInfo(id: Int): IngredientInfo
    fun getIngredientWithAmountList(recipeId: Int): LiveData<List<IngredientWithAmountInfo>>
    fun getStepsListByRecipeId(recipeId: Int): LiveData<List<StepInfo>>
    fun getFavouriteRecipesList():LiveData<List<RecipeInfo>>
    suspend fun addRecipe(recipe: RecipeInfo)
    suspend fun removeRecipe(recipe: RecipeInfo)
    suspend fun editRecipe(recipe: RecipeInfo)
    suspend fun getRecipeWithSteps(id: Int): RecipeWithStepsInfo
    suspend fun getStepWithIngredients(name: String): StepWithIngredientsInfo
    suspend fun loadData(cuisine: String)
    fun getUser(id: String): LiveData<UserInfo>
    suspend fun deleteUser(user: UserInfo)
    suspend fun insertUser(user: UserInfo)
    suspend fun editUser(user: UserInfo)
    suspend fun getPopularRecipe(): RecipeInfo?
}