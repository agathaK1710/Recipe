package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetRecipesListUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(cuisine: String) = repository.getRecipesList(cuisine)
}