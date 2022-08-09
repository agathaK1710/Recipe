package com.android.recipe.data.network.model

import com.google.gson.annotations.SerializedName

data class StepDto(
    val number: Int,
    @SerializedName("step")
    val stepDescription: String,
    val ingredients: List<IngredientDto>,
    val equipment: List<EquipmentDto>
)