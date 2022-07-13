package com.android.recipe.domain

class GetRecipesListUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke() = repository.getRecipeList()
}