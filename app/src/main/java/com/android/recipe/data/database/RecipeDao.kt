package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.database.relations.RecipeWithIngredients
import com.android.recipe.data.database.relations.RecipeWithSteps
import com.android.recipe.data.database.relations.StepWithIngredients

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getRecipeList(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE recipeId == :id LIMIT 1")
    fun getRecipeById(id: Int): LiveData<RecipeEntity>

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun editRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeList(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId == :id")
    fun getRecipeWithIngredients(id: Int): RecipeWithIngredients

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId == :id")
    fun getRecipeWithSteps(id: Int): RecipeWithSteps

    @Transaction
    @Query("SELECT * FROM steps WHERE stepId == :id")
    fun getStepWithIngredients(id: Int): StepWithIngredients
}