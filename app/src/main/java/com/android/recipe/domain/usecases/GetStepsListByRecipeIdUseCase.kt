package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetStepsListByRecipeIdUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) = repository.getStepsListByRecipeId(recipeId)
}