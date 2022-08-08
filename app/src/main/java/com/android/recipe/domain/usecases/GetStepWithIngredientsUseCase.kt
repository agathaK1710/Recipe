package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetStepWithIngredientsUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(name: String) = repository.getStepWithIngredients(name)
}