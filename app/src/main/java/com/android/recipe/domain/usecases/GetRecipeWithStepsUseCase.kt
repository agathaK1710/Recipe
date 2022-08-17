package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetRecipeWithStepsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) = repository.getRecipeWithSteps(id)
}