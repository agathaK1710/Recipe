package com.android.recipe.domain

class RemoveRecipeUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipe: RecipeInfo) = repository.removeRecipe(recipe)
}