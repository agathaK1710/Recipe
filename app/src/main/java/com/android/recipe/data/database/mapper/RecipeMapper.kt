package com.android.recipe.data.database.mapper

import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.network.model.MealDto
import com.android.recipe.data.network.model.NutritionListDto
import com.android.recipe.data.network.model.RecipeDetailDto
import com.android.recipe.data.network.model.RecipeDto
import com.android.recipe.domain.RecipeInfo

class RecipeMapper {
    fun mapDtoToRecipeEntity(
        recipeDto: RecipeDto,
        recipeDetailDto: RecipeDetailDto,
        mealDto: MealDto
    ) = RecipeEntity(
        recipeId = recipeDto.id,
        image = recipeDto.image,
        likes = recipeDto.likes,
        title = recipeDto.title,
        healthScore = recipeDetailDto.healthScore,
        readyInMinutes = recipeDetailDto.readyInMinutes,
        servings = recipeDetailDto.servings,
        dishTypes = recipeDetailDto.dishTypes.joinToString(", "),
        instructions = recipeDetailDto.instructions,
        calories = getEnergyValueFromNutritionList(mealDto.nutrition, 0),
        carbs = getEnergyValueFromNutritionList(mealDto.nutrition, 1),
        fat = getEnergyValueFromNutritionList(mealDto.nutrition, 2),
        protein = getEnergyValueFromNutritionList(mealDto.nutrition, 3)
        )

    private fun getEnergyValueFromNutritionList(nutritionListDto: NutritionListDto, index: Int) =
        with(nutritionListDto.nutrients[index]) {
            amount
        }

    fun mapEntityToInfo(recipeEntity: RecipeEntity) = RecipeInfo(
        id = recipeEntity.recipeId,
        image = recipeEntity.image,
        likes = recipeEntity.likes,
        title = recipeEntity.title,
        healthScore = recipeEntity.healthScore,
        readyInMinutes = recipeEntity.readyInMinutes,
        servings = recipeEntity.servings,
        dishTypes = recipeEntity.dishTypes,
        instructions = recipeEntity.instructions,
        calories = recipeEntity.calories,
        carbs = recipeEntity.carbs,
        fat = recipeEntity.fat,
        protein = recipeEntity.protein
    )

    fun mapInfoToEntity(recipeInfo: RecipeInfo) = RecipeEntity(
        recipeId = recipeInfo.id,
        image = recipeInfo.image,
        likes = recipeInfo.likes,
        title = recipeInfo.title,
        healthScore = recipeInfo.healthScore,
        readyInMinutes = recipeInfo.readyInMinutes,
        servings = recipeInfo.servings,
        dishTypes = recipeInfo.dishTypes,
        instructions = recipeInfo.instructions,
        calories = recipeInfo.calories,
        carbs = recipeInfo.carbs,
        fat = recipeInfo.fat,
        protein = recipeInfo.protein
    )
}