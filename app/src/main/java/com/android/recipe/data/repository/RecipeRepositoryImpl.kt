package com.android.recipe.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.database.mapper.RecipeMapper
import com.android.recipe.data.network.ApiFactory
import com.android.recipe.domain.RecipeInfo
import com.android.recipe.domain.RecipeRepository

class RecipeRepositoryImpl(
    application: Application
) : RecipeRepository {
    private val apiService = ApiFactory.apiService
    private val mapper = RecipeMapper()
    private val recipeDao = AppDatabase.getInstance(application).recipeDao()
    override fun getRecipeList(): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getRecipeList()){
            it.map {
                mapper.mapEntityToInfo(it)
            }
        }
    }

    override fun getRecipeInfo(id: Int): LiveData<RecipeInfo> {
        return Transformations.map(recipeDao.getRecipeById(id)){
            mapper.mapEntityToInfo(it)
        }
    }

    override fun addRecipe(recipe: RecipeInfo) {
        recipeDao.insertRecipe(mapper.mapInfoToEntity(recipe))
    }

    override fun removeRecipe(recipe: RecipeInfo) {
        recipeDao.deleteRecipe(mapper.mapInfoToEntity(recipe))
    }

    override fun editRecipe(recipe: RecipeInfo) {
        recipeDao.editRecipe(mapper.mapInfoToEntity(recipe))
    }
}