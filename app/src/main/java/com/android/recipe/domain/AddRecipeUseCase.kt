package com.android.recipe.domain

class AddRecipeUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipe: RecipeInfo) = repository.addRecipe(recipe)
}