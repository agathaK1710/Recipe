package com.android.recipe.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.recipe.data.database.RecipeDao
import com.android.recipe.data.database.UserDao
import com.android.recipe.data.database.entities.RecipeIngredientRatio
import com.android.recipe.data.database.entities.StepIngredientRatio
import com.android.recipe.data.database.mappers.IngredientMapper
import com.android.recipe.data.database.mappers.RecipeMapper
import com.android.recipe.data.database.mappers.StepMapper
import com.android.recipe.data.database.mappers.UserMapper
import com.android.recipe.data.network.ApiFactory
import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeMapper: RecipeMapper,
    private val stepMapper: StepMapper,
    private val ingredientMapper: IngredientMapper,
    private val userMapper: UserMapper,
    private val recipeDao: RecipeDao,
    private val userDao: UserDao
) : RecipeRepository {
    private val apiService = ApiFactory.apiService

    override fun getRecipesList(cuisine: String): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getRecipesList(cuisine)) { list ->
            list.map {
                recipeMapper.mapRecipeEntityToInfo(it)
            }
        }
    }

    override fun getFavouriteRecipesList(): LiveData<List<RecipeInfo>> {
        return Transformations.map(recipeDao.getFavouriteRecipesList()) { list ->
            list.map {
                recipeMapper.mapRecipeEntityToInfo(it)
            }
        }
    }

    override suspend fun getRecipeInfo(id: Int): RecipeInfo {
        return recipeMapper.mapRecipeEntityToInfo(recipeDao.getRecipeById(id))
    }

    override suspend fun addRecipe(recipe: RecipeInfo) {
        recipeDao.insertRecipe(recipeMapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun removeRecipe(recipe: RecipeInfo) {
        recipeDao.deleteRecipe(recipeMapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun editRecipe(recipe: RecipeInfo) {
        recipeDao.editRecipe(recipeMapper.mapRecipeInfoToEntity(recipe))
    }

    override suspend fun getRecipeWithSteps(id: Int): RecipeWithStepsInfo {
        return recipeMapper.mapRecipeStepsEntityToInfo(recipeDao.getRecipeWithSteps(id))

    }

    override suspend fun getStepWithIngredients(name: String): StepWithIngredientsInfo {
        return stepMapper.mapStepIngredientsEntityToInfo(recipeDao.getStepWithIngredients(name))
    }

    override fun getIngredientWithAmountList(recipeId: Int): LiveData<List<IngredientWithAmountInfo>> {
        return Transformations.map(recipeDao.getIngredientWithAmountList(recipeId)) { list ->
            list.map {
                recipeMapper.mapRecipeIngredientEntityToInfo(it)
            }
        }
    }

    override suspend fun getIngredientInfo(id: Int): IngredientInfo {
        return ingredientMapper.mapIngredientEntityToInfo(recipeDao.getIngredientById(id))
    }

    override fun getStepsListByRecipeId(recipeId: Int): LiveData<List<StepInfo>> {
        return Transformations.map(recipeDao.getStepsListByRecipeId(recipeId)) { list ->
            list.map {
                stepMapper.mapStepEntityToInfo(it)
            }
        }
    }

    override suspend fun loadData(cuisine: String): Unit = withContext(Dispatchers.IO) {
        try {
            val recipes = apiService.searchRecipeByCuisine(cuisine = cuisine).recipes.map {
                recipeMapper.mapDtoToRecipeEntity(
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
                    ingredientMapper.mapIngredientDtoToEntity(
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
                        stepMapper.mapStepDtoToEntity(it, id)
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

    override fun getUser(id: String): LiveData<UserInfo> {
        return Transformations.map(userDao.getUser(id)) {
            userMapper.mapUserEntityToInfo(it)
        }
    }

    override suspend fun deleteUser(user: UserInfo) {
        userMapper.mapUserInfoToEntity(user)?.let { userDao.deleteUser(it) }
    }

    override suspend fun insertUser(user: UserInfo) {
        userMapper.mapUserInfoToEntity(user)?.let { userDao.insertUser(it) }
    }

    override suspend fun editUser(user: UserInfo) {
        userMapper.mapUserInfoToEntity(user)?.let { userDao.editUser(it) }
    }
}