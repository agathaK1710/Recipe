package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class GetIngredientWithAmountUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) =
        repository.getIngredientWithAmountList(recipeId)
}