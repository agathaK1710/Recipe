package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository

class GetIngredientInfoUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) = repository.getIngredientInfo(id)
}