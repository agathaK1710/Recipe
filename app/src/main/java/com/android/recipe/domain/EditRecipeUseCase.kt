package com.android.recipe.domain

class EditRecipeUseCase(
    private val repository: RecipeRepository
) {
     operator fun invoke(recipe: RecipeInfo) = repository.editRecipe(recipe)
}