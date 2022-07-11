package com.android.recipe.data.database.mapper

import com.android.recipe.data.network.model.MealDto
import com.android.recipe.data.network.model.RecipeDto

class RecipeMapper {
    fun mealListDtoToRecipeListDto(meal: List<MealDto>): List<RecipeDto> {
        return meal.map {
            RecipeDto(
                id = it.id,
                image = it.image,
                title = it.title,
                likes = 0,
                missedIngredientCount = 0,
                missedIngredients = null,
                usedIngredientCount = 0,
                usedIngredients = null,
                nutrients = it.nutrition.nutrients
            )
        }
    }
}