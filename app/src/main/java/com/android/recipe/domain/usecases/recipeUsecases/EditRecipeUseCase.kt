package com.android.recipe.domain.usecases.recipeUsecases

import com.android.recipe.domain.RecipeRepository
import com.android.recipe.domain.entities.RecipeInfo
import javax.inject.Inject

class EditRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
     suspend operator fun invoke(recipe: RecipeInfo) = repository.editRecipe(recipe)
}