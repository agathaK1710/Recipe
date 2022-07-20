package com.android.recipe.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.recipe.data.repository.RecipeRepositoryImpl
import com.android.recipe.domain.AddRecipeUseCase
import com.android.recipe.domain.GetRecipeInfoUseCase
import com.android.recipe.domain.GetRecipesListUseCase
import com.android.recipe.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val getRecipesListUseCase = GetRecipesListUseCase(repository)
    private val getRecipeInfoUseCase = GetRecipeInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val addRecipeUseCase = AddRecipeUseCase(repository)

   fun getRecipeInfo(id: Int) = getRecipeInfoUseCase(id)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}