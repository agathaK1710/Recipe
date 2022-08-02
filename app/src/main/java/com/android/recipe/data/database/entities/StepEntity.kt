package com.android.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val stepId: Int = 0,
    val recipeInfoId: Int,
    val description: String,
    val number: Int,
    val equipments: String
)