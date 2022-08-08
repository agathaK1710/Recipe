package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetIngredientWithAmountUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) =
        repository.getIngredientWithAmountList(recipeId)
}