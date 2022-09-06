package com.android.recipe.data.database.mappers

import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.database.entities.RecipeIngredientRatio
import com.android.recipe.data.database.relations.RecipeWithSteps
import com.android.recipe.data.network.model.NutritionListDto.Nutrition.*
import com.android.recipe.data.network.model.RecipeDetailDto
import com.android.recipe.domain.entities.IngredientWithAmountInfo
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.domain.entities.RecipeWithStepsInfo
import javax.inject.Inject

class RecipeMapper @Inject constructor(
    private val stepMapper: StepMapper
) {

    fun mapDtoToRecipeEntity(
        recipeDetailDto: RecipeDetailDto,
    ) = RecipeEntity(
        recipeId = recipeDetailDto.id,
        image = recipeDetailDto.image,
        title = recipeDetailDto.title,
        favouriteRecipe = false,
        popularRecipe = false,
        likes = recipeDetailDto.likes,
        healthScore = recipeDetailDto.healthScore,
        readyInMinutes = recipeDetailDto.readyInMinutes,
        servings = recipeDetailDto.servings,
        dishTypes = recipeDetailDto.dishTypes.joinToString(", "),
        instructions = recipeDetailDto.instructions,
        calories = recipeDetailDto.nutrition.getNutrientAmount(CALORIES),
        fat = recipeDetailDto.nutrition.getNutrientAmount(FAT),
        protein = recipeDetailDto.nutrition.getNutrientAmount(PROTEIN),
        carbs = recipeDetailDto.nutrition.getNutrientAmount(CARBOHYDRATES),
        cuisine = recipeDetailDto.cuisines.joinToString(", ")
    )





    fun mapRecipeEntityToInfo(recipeEntity: RecipeEntity) = RecipeInfo(
        id = recipeEntity.recipeId,
        favouriteRecipe = recipeEntity.favouriteRecipe,
        popularRecipe = recipeEntity.popularRecipe,
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
        protein = recipeEntity.protein,
        cuisine = recipeEntity.cuisine
    )

    fun mapRecipeInfoToEntity(recipeInfo: RecipeInfo) = RecipeEntity(
        recipeId = recipeInfo.id,
        image = recipeInfo.image,
        likes = recipeInfo.likes,
        favouriteRecipe = recipeInfo.favouriteRecipe,
        popularRecipe = recipeInfo.popularRecipe,
        title = recipeInfo.title,
        healthScore = recipeInfo.healthScore,
        readyInMinutes = recipeInfo.readyInMinutes,
        servings = recipeInfo.servings,
        dishTypes = recipeInfo.dishTypes,
        instructions = recipeInfo.instructions,
        calories = recipeInfo.calories,
        carbs = recipeInfo.carbs,
        fat = recipeInfo.fat,
        protein = recipeInfo.protein,
        cuisine = recipeInfo.cuisine
    )

    fun mapRecipeIngredientEntityToInfo(
        recipeWithIngredients: RecipeIngredientRatio
    ) =
        IngredientWithAmountInfo(
            recipeId = recipeWithIngredients.ingredientId,
            ingredientId = recipeWithIngredients.ingredientId,
            amount = recipeWithIngredients.amount,
            unit = recipeWithIngredients.unit
        )

    fun mapRecipeStepsEntityToInfo(recipeWithSteps: RecipeWithSteps) = RecipeWithStepsInfo(
        recipe = mapRecipeEntityToInfo(recipeWithSteps.recipe),
        steps = recipeWithSteps.steps?.map {
            stepMapper.mapStepEntityToInfo(it)
        }
    )
}