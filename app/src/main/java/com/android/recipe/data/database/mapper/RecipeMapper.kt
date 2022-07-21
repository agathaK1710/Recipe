package com.android.recipe.data.database.mapper

import com.android.recipe.data.database.entities.IngredientEntity
import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.database.entities.StepEntity
import com.android.recipe.data.database.relations.RecipeWithIngredients
import com.android.recipe.data.database.relations.RecipeWithSteps
import com.android.recipe.data.database.relations.StepWithIngredients
import com.android.recipe.data.network.model.NutritionListDto
import com.android.recipe.data.network.model.RecipeDetailDto
import com.android.recipe.domain.entities.*

class RecipeMapper {
    fun mapDtoToRecipeEntity(
        recipeDetailDto: RecipeDetailDto,
    ) = RecipeEntity(
        recipeId = recipeDetailDto.id,
        image = recipeDetailDto.image,
        title = recipeDetailDto.title,
        likes = recipeDetailDto.likes,
        healthScore = recipeDetailDto.healthScore,
        readyInMinutes = recipeDetailDto.readyInMinutes,
        servings = recipeDetailDto.servings,
        dishTypes = recipeDetailDto.dishTypes.joinToString(", "),
        instructions = recipeDetailDto.instructions,
        calories = getEnergyValueFromNutritionList(recipeDetailDto.nutrition)["Calories"] ?: 0.0,
        carbs = getEnergyValueFromNutritionList(recipeDetailDto.nutrition)["Carbohydrates"] ?: 0.0,
        fat = getEnergyValueFromNutritionList(recipeDetailDto.nutrition)["Fat"] ?: 0.0,
        protein = getEnergyValueFromNutritionList(recipeDetailDto.nutrition)["Protein"] ?: 0.0
    )

    private fun getEnergyValueFromNutritionList(nutritionListDto: NutritionListDto?): Map<String, Double> {
        val map = HashMap<String, Double>()
        nutritionListDto?.nutrients?.forEach {
            when (it.name) {
                "Calories" -> map["Calories"] = it.amount
                "Fat" -> map["Fat"] = it.amount
                "Protein" -> map["Protein"] = it.amount
                "Carbohydrates" -> map["Carbohydrates"] = it.amount
            }
        }
        return map
    }


    fun mapRecipeEntityToInfo(recipeEntity: RecipeEntity) = RecipeInfo(
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

    fun mapRecipeInfoToEntity(recipeInfo: RecipeInfo) = RecipeEntity(
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

    private fun mapIngredientEntityToInfo(ingredientEntity: IngredientEntity) = IngredientInfo(
        id = ingredientEntity.ingredientId,
        name = ingredientEntity.name,
        image = ingredientEntity.image
    )

    private fun mapStepEntityToInfo(stepEntity: StepEntity) = StepInfo(
        id = stepEntity.stepId,
        recipeId = stepEntity.recipeInfoId,
        description = stepEntity.description,
        number = stepEntity.number,
        equipments = stepEntity.equipments
    )

    fun mapRecipeIngredientEntityToInfo(recipeWithIngredients: RecipeWithIngredients) =
        RecipeWithIngredientsInfo(
            recipe = mapRecipeEntityToInfo(recipeWithIngredients.recipe),
            ingredients = recipeWithIngredients.ingredients?.map {
                mapIngredientEntityToInfo(it)
            }
        )

    fun mapRecipeStepsEntityToInfo(recipeWithSteps: RecipeWithSteps) = RecipeWithStepsInfo(
        recipe = mapRecipeEntityToInfo(recipeWithSteps.recipe),
        steps = recipeWithSteps.steps?.map {
            mapStepEntityToInfo(it)
        }
    )

    fun mapStepIngredientsEntityToIndo(stepWithIngredients: StepWithIngredients) =
        StepWithIngredientsInfo(
            step = mapStepEntityToInfo(stepWithIngredients.step),
            ingredients = stepWithIngredients.ingredients.map {
                mapIngredientEntityToInfo(it)
            }
        )
}