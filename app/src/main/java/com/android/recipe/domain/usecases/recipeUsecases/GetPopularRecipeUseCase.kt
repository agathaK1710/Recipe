package com.android.recipe.domain.usecases.recipeUsecases

import com.android.recipe.data.repository.LocalRepositoryImpl
import javax.inject.Inject

class GetPopularRecipeUseCase @Inject constructor(
    private val repository: LocalRepositoryImpl
) {
    suspend operator fun invoke() = repository.getPopularRecipe()
}