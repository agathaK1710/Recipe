package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetRecipeWithIngredientsUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int) = repository.getRecipeWithIngredients(id)
}