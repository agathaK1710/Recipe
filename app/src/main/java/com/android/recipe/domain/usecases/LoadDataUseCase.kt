package com.android.recipe.domain.usecases

import com.android.recipe.domain.RecipeRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(cuisine: String) = repository.loadData(cuisine)
}