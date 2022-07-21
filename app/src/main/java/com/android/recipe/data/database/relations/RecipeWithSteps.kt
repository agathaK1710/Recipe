package com.android.recipe.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.database.entities.StepEntity

data class RecipeWithSteps(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeInfoId"
    )
    val steps: List<StepEntity>?
)
