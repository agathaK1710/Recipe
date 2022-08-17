package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetFavouriteRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getFavouriteRecipesList()
}