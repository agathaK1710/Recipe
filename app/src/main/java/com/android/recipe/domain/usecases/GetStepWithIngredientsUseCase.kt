package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetStepWithIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(name: String) = repository.getStepWithIngredients(name)
}