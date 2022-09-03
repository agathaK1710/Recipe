package com.android.recipe.domain.usecases.recipeUsecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetStepsListByRecipeIdUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) = repository.getStepsListByRecipeId(recipeId)
}