package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.recipe.data.network.model.RecipeDto

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY likes")
    fun getRecipeList(): LiveData<List<RecipeDto>>

    @Query("SELECT * FROM recipes WHERE recipeId == :id LIMIT 1")
    fun getRecipeById(id: Int): LiveData<RecipeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeList(recipe: List<RecipeDto>)
}