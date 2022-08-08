package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetRecipeWithStepsUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) = repository.getRecipeWithSteps(id)
}