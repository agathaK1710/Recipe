package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.RecipeInfo
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeInfo) = repository.addRecipe(recipe)
}