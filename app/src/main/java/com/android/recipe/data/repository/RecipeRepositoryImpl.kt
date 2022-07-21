package com.android.recipe.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.database.mapper.RecipeMapper
import com.android.recipe.data.network.ApiFactory
import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.entities.RecipeWithIngredientsInfo
import com.android.recipe.domain.entities.RecipeWithStepsInfo
import com.android.recipe.domain.entities.StepWithIngredientsInfo

class RecipeRepositoryImpl(
    application: Application
) : RecipeRepository {
    private val apiService = ApiFactory.apiService
    private val mapper = RecipeMapper()
    private val recipeDao = AppDatabase.getInstance(application).recipeDao()
    override fun getRecipeList(): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getRecipeList()) {
            it.map {
                mapper.mapRecipeEntityToInfo(it)
            }
        }
    }

    override fun getRecipeInfo(id: Int): LiveData<RecipeInfo> {
        return Transformations.map(recipeDao.getRecipeById(id)) {
            mapper.mapRecipeEntityToInfo(it)
        }
    }

    override suspend fun addRecipe(recipe: RecipeInfo) {
        recipeDao.insertRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override fun removeRecipe(recipe: RecipeInfo) {
        recipeDao.deleteRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override fun editRecipe(recipe: RecipeInfo) {
        recipeDao.editRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override fun getRecipeWithIngredients(id: Int): LiveData<RecipeWithIngredientsInfo> {
        return Transformations.map(recipeDao.getRecipeWithIngredients(id)){
            mapper.mapRecipeIngredientEntityToInfo(it)
        }
    }

    override fun getRecipeWithSteps(id: Int): LiveData<RecipeWithStepsInfo> {
        return Transformations.map(recipeDao.getRecipeWithSteps(id)){
            mapper.mapRecipeStepsEntityToInfo(it)
        }
    }

    override fun getStepWithIngredients(id: Int): LiveData<StepWithIngredientsInfo> {
        return Transformations.map(recipeDao.getStepWithIngredients(id)){
            mapper.mapStepIngredientsEntityToIndo(it)
        }
    }

    override suspend fun loadData() {
        try {
            val randomRecipes = apiService.getRandomRecipes(number = 20).randomRecipesId.map {
                mapper.mapDtoToRecipeEntity(
                    apiService.getRecipeInformation(
                        it.id,
                        includeNutrition = true
                    )
                )
            }
            recipeDao.insertRecipeList(randomRecipes)

        } catch (e: Exception) {
            Log.d("TAG", "Some went wrong")
        }
    }
}