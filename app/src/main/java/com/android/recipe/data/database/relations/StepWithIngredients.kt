package com.android.recipe.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.android.recipe.data.database.entities.IngredientEntity
import com.android.recipe.data.database.entities.StepEntity
import com.android.recipe.data.database.entities.StepIngredientRatio

data class StepWithIngredients(
    @Embedded val step: StepEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "ingredientId",
        associateBy = Junction(StepIngredientRatio::class)
    )
    val ingredients: List<IngredientEntity>
)
