package com.android.recipe.data.database.mapper

import com.android.recipe.data.database.entities.IngredientEntity
import com.android.recipe.data.network.model.IngredientDto
import com.android.recipe.domain.entities.IngredientInfo
import javax.inject.Inject

class IngredientMapper @Inject constructor(){
    fun mapIngredientDtoToEntity(ingredientDto: IngredientDto) = IngredientEntity(
        ingredientId = ingredientDto.id,
        name = ingredientDto.name,
        image = ingredientDto.image
    )
    fun mapIngredientEntityToInfo(
        ingredientEntity: IngredientEntity
    ) = IngredientInfo(
        id = ingredientEntity.ingredientId,
        name = ingredientEntity.name,
        image = ingredientEntity.image
    )
}