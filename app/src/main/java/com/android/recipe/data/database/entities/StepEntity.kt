package com.android.recipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey
    val name: String,
    val recipeInfoId: Int,
    val number: Int,
    val equipments: String
)