package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetRecipesListUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(cuisine: String) = repository.getRecipesList(cuisine)
}