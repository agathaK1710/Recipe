package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstructionDto(
    @SerializedName("steps")
    @Expose
    val steps: List<StepDto>
)
