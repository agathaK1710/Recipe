package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.recipe.data.database.entities.*
import com.android.recipe.data.database.relations.RecipeWithSteps
import com.android.recipe.data.database.relations.StepWithIngredients

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY likes")
    fun getRecipesList(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE recipeId == :id LIMIT 1")
    suspend fun getRecipeById(id: Int): RecipeEntity

    @Query("SELECT * FROM ingredients WHERE ingredientId == :id LIMIT 1")
    suspend fun getIngredientById(id: Int): IngredientEntity

    @Query("SELECT * FROM steps WHERE recipeInfoId == :id")
    fun getStepsListByRecipeId(id: Int): LiveData<List<StepEntity>>

    @Query("SELECT * FROM recipeWithIngredients WHERE recipeId == :recipeId")
    fun getIngredientWithAmountList(recipeId: Int): LiveData<List<RecipeIngredientRatio>>

    @Query("SELECT * FROM recipes WHERE favouriteRecipe == 1")
    fun getFavouriteRecipesList(): LiveData<List<RecipeEntity>>

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

//    @Transaction
//    @Query("SELECT * FROM recipes WHERE recipeId == :id")
//    fun getRecipeWithIngredients(id: Int): RecipeWithIngredients

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId == :id")
    fun getRecipeWithSteps(id: Int): RecipeWithSteps

    @Transaction
    @Query("SELECT * FROM steps WHERE name == :name")
    fun getStepWithIngredients(name: String): StepWithIngredients
}