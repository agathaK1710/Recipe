package com.android.recipe.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.android.recipe.data.database.entities.IngredientEntity
import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.database.entities.RecipeIngredientRatio

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredientRatio::class)
    )
    val ingredients: List<IngredientEntity>
)