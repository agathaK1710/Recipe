package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.recipe.data.database.entities.*
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
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipesList(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStepsList(steps: List<StepEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientsList(ingredients: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Insert
    suspend fun insertRecipeIngredientRatio(recipeIngredientRatio: RecipeIngredientRatio)

    @Insert
    suspend fun insertStepIngredientRatio(stepIngredientRatio: StepIngredientRatio)

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId == :id")
    fun getRecipeWithIngredients(id: Int): LiveData<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId == :id")
    fun getRecipeWithSteps(id: Int): LiveData<RecipeWithSteps>

    @Transaction
    @Query("SELECT * FROM steps WHERE name == :name")
    fun getStepWithIngredients(name: String): LiveData<StepWithIngredients>
}