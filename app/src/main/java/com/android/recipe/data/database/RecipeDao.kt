package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.recipe.data.database.entities.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY likes")
    fun getRecipeList(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE recipeId == :id LIMIT 1")
    fun getRecipeById(id: Int): LiveData<RecipeEntity>

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun editRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity)
}