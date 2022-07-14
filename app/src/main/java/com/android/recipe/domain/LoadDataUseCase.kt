package com.android.recipe.domain

class LoadDataUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.loadData()
}