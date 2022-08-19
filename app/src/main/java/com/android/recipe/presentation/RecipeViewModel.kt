package com.android.recipe.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.recipe.data.repository.RecipeRepositoryImpl
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.usecases.*
import com.android.recipe.presentation.adapters.CuisineAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val application: Application,
    private val getRecipesListUseCase: GetRecipesListUseCase,
    private val getRecipeInfoUseCase: GetRecipeInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val addRecipeUseCase: AddRecipeUseCase,
    private val removeRecipeUseCase: RemoveRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase,
    private val getIngredientWithAmountListUseCase: GetIngredientWithAmountUseCase,
    private val getRecipeWithStepsUseCase: GetRecipeWithStepsUseCase,
    private val getIngredientInfoUseCase: GetIngredientInfoUseCase,
    private val getStepsListByRecipeIdUseCase: GetStepsListByRecipeIdUseCase,
    private val getStepWithIngredientsUseCase: GetStepWithIngredientsUseCase,
    getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase
) : ViewModel() {

    val name = MutableLiveData<String>()
    val cuisineAdapter = MutableLiveData<CuisineAdapter>()

    val favouriteRecipesList = getFavouriteRecipesUseCase()
    suspend fun getRecipeInfo(id: Int) = getRecipeInfoUseCase(id)
    suspend fun getIngredientInfo(id: Int) = getIngredientInfoUseCase(id)
    suspend fun editRecipe(recipe: RecipeInfo) = editRecipeUseCase(recipe)
    suspend fun removeRecipe(recipe: RecipeInfo) = removeRecipeUseCase(recipe)
    fun getIngredientWithAmountList(recipeId: Int) =
        getIngredientWithAmountListUseCase(recipeId)

    fun getRecipesByCuisine(cuisine: String) = getRecipesListUseCase(cuisine)

    fun getStepsListByRecipeId(id: Int) = getStepsListByRecipeIdUseCase(id)
    suspend fun getStepWithIngredients(name: String) = getStepWithIngredientsUseCase(name)
    suspend fun loadData(cuisine: String) = loadDataUseCase(cuisine)
}
