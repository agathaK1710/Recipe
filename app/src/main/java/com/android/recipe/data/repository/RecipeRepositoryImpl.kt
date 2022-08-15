package com.android.recipe.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.database.entities.RecipeIngredientRatio
import com.android.recipe.data.database.entities.StepIngredientRatio
import com.android.recipe.data.database.mapper.RecipeMapper
import com.android.recipe.data.network.ApiFactory
import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    application: Application,
) : RecipeRepository {
    private val apiService = ApiFactory.apiService
    private val mapper = RecipeMapper()
    private val recipeDao = AppDatabase.getInstance(application).recipeDao()

    override fun getRecipesList(cuisine: String): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getRecipesList(cuisine)) { list ->
            list.map {
                mapper.mapRecipeEntityToInfo(it)
            }
        }
    }

    override fun getFavouriteRecipesList(): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getFavouriteRecipesList()) { list ->
            list.map {
                mapper.mapRecipeEntityToInfo(it)
            }
        }
    }

    override suspend fun getRecipeInfo(id: Int): RecipeInfo {
        return mapper.mapRecipeEntityToInfo(recipeDao.getRecipeById(id))
    }

    override suspend fun addRecipe(recipe: RecipeInfo) {
        recipeDao.insertRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun removeRecipe(recipe: RecipeInfo) {
        recipeDao.deleteRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun editRecipe(recipe: RecipeInfo) {
        recipeDao.editRecipe(mapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun getRecipeWithSteps(id: Int): RecipeWithStepsInfo {
        return mapper.mapRecipeStepsEntityToInfo(recipeDao.getRecipeWithSteps(id))

    }

    override suspend fun getStepWithIngredients(name: String): StepWithIngredientsInfo {
        return mapper.mapStepIngredientsEntityToIndo(recipeDao.getStepWithIngredients(name))
    }

    override fun getIngredientWithAmountList(recipeId: Int): LiveData<List<IngredientWithAmountInfo>> {
        return Transformations.map(recipeDao.getIngredientWithAmountList(recipeId)) { list ->
            list.map {
                mapper.mapRecipeIngredientEntityToInfo(it)
            }
        }
    }

    override suspend fun getIngredientInfo(id: Int): IngredientInfo {
        return mapper.mapIngredientEntityToInfo(recipeDao.getIngredientById(id))
    }

    override fun getStepsListByRecipeId(recipeId: Int): LiveData<List<StepInfo>> {
        return Transformations.map(recipeDao.getStepsListByRecipeId(recipeId)) { list ->
            list.map {
                mapper.mapStepEntityToInfo(it)
            }
        }
    }

    override suspend fun loadData(cuisine:String): Unit = withContext(Dispatchers.IO) {
        try {
            val recipes = apiService.searchRecipeByCuisine(cuisine = cuisine).recipes.map {
                mapper.mapDtoToRecipeEntity(
                    apiService.getRecipeInformation(
                        it.id,
                        includeNutrition = true
                    )
                )
            }
            recipeDao.insertRecipesList(recipes)
            val recipeIds = recipes.map {
                it.recipeId
            }
            for (id in recipeIds) {
                val recipeInformation = apiService.getRecipeInformation(
                    id,
                    includeNutrition = true
                )

                val ingredients = recipeInformation.extendedIngredients
                recipeDao.insertIngredientsList(ingredients.map {
                    mapper.mapIngredientDtoToEntity(
                        it
                    )
                })

                for (ingredient in ingredients) {
                    recipeDao.insertRecipeIngredientRatio(
                        RecipeIngredientRatio(
                            id,
                            ingredient.id,
                            ingredient.amount,
                            ingredient.unit
                        )
                    )
                }

                val steps = recipeInformation.analyzedInstructions[0].steps
                recipeDao.insertStepsList(
                    steps.map {
                        mapper.mapStepDtoToEntity(it, id)
                    }
                )

                for (step in steps) {
                    for (ingredient in step.ingredients) {
                        recipeDao.insertStepIngredientRatio(
                            StepIngredientRatio(
                                step.stepDescription,
                                ingredient.id
                            )
                        )
                    }
                }
            }

        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }
}