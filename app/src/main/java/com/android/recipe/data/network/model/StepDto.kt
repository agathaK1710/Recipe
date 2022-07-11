package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StepDto(
    @SerializedName("number")
    @Expose
    val number: Int,
    @SerializedName("step")
    @Expose
    val nameStep: String,
    @SerializedName("ingredients")
    @Expose
    val ingredients: List<IngredientDto>,
    @SerializedName("equipment")
    @Expose
    val equipments: List<EquipmentDto>
)