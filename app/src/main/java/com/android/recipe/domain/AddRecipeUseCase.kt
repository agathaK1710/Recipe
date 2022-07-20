package com.android.recipe.domain

class AddRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeInfo) = repository.addRecipe(recipe)
}