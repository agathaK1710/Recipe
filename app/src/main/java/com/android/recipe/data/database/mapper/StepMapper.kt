package com.android.recipe.data.database.mapper

import com.android.recipe.data.database.entities.StepEntity
import com.android.recipe.data.database.relations.StepWithIngredients
import com.android.recipe.data.network.model.StepDto
import com.android.recipe.domain.entities.StepInfo
import com.android.recipe.domain.entities.StepWithIngredientsInfo
import javax.inject.Inject

class StepMapper  @Inject constructor(
    private val ingredientMapper: IngredientMapper
){
    fun mapStepDtoToEntity(stepDto: StepDto, recipeId: Int) = StepEntity(
        recipeInfoId = recipeId,
        name = stepDto.stepDescription,
        number = stepDto.number,
        equipments = stepDto.equipment.joinToString(", ") { it.name }
    )

    fun mapStepEntityToInfo(stepEntity: StepEntity) = StepInfo(
        recipeId = stepEntity.recipeInfoId,
        description = stepEntity.name,
        number = stepEntity.number,
        equipments = stepEntity.equipments
    )

    fun mapStepIngredientsEntityToInfo(
        stepWithIngredients: StepWithIngredients
    ) =
        StepWithIngredientsInfo(
            step = mapStepEntityToInfo(stepWithIngredients.step),
            ingredients = stepWithIngredients.ingredients.map {
                ingredientMapper.mapIngredientEntityToInfo(it)
            }
        )
}