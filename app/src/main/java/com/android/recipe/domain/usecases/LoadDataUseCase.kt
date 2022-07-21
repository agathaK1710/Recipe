package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class LoadDataUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.loadData()
}