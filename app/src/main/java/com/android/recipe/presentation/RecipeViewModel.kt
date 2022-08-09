package com.android.recipe.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.recipe.data.repository.RecipeRepositoryImpl
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val getRecipesListUseCase = GetRecipesListUseCase(repository)
    private val getRecipeInfoUseCase = GetRecipeInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val addRecipeUseCase = AddRecipeUseCase(repository)
    private val removeRecipeUseCase = RemoveRecipeUseCase(repository)
    private val editRecipeUseCase = EditRecipeUseCase(repository)
    private val getIngredientWithAmountListUseCase = GetIngredientWithAmountUseCase(repository)
    private val getRecipeWithStepsUseCase = GetRecipeWithStepsUseCase(repository)
    private val getIngredientInfoUseCase = GetIngredientInfoUseCase(repository)
    private val getStepsListByRecipeIdUseCase = GetStepsListByRecipeIdUseCase(repository)
    private val getStepWithIngredientsUseCase = GetStepWithIngredientsUseCase(repository)
    val recipesList = getRecipesListUseCase()


    suspend fun getRecipeInfo(id: Int) = getRecipeInfoUseCase(id)
    suspend fun getIngredientInfo(id: Int) = getIngredientInfoUseCase(id)
    suspend fun editRecipe(recipe: RecipeInfo) = editRecipeUseCase(recipe)
    suspend fun removeRecipe(recipe: RecipeInfo) = removeRecipeUseCase(recipe)
    fun getIngredientWithAmountList(recipeId: Int) =
        getIngredientWithAmountListUseCase(recipeId)
    fun getStepsListByRecipeId(id: Int) = getStepsListByRecipeIdUseCase(id)
    suspend fun getStepWithIngredients(name: String) = getStepWithIngredientsUseCase(name)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}
