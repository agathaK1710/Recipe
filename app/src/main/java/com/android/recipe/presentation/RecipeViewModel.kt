package com.android.recipe.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.recipe.data.repository.RecipeRepositoryImpl
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.usecases.*
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val getRecipesListUseCase = GetRecipesListUseCase(repository)
    private val getRecipeInfoUseCase = GetRecipeInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val addRecipeUseCase = AddRecipeUseCase(repository)
    private val removeRecipeUseCase = RemoveRecipeUseCase(repository)

    val recipesList = getRecipesListUseCase()

    fun getRecipeInfo(id: Int) = getRecipeInfoUseCase(id)
    suspend fun removeRecipe(recipe: RecipeInfo) = removeRecipeUseCase(recipe)
    suspend fun loadData() = loadDataUseCase()

//    init {
//        viewModelScope.launch{
//            loadDataUseCase()
//        }
//    }

}