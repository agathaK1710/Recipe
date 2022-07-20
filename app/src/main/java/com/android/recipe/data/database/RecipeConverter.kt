package com.android.recipe.data.database

import androidx.room.TypeConverter
import com.android.recipe.data.database.entities.RecipeEntity
import com.android.recipe.data.network.model.EquipmentDto
import com.android.recipe.data.network.model.IngredientDto
import com.android.recipe.data.network.model.NutrientDto
import com.android.recipe.data.network.model.NutritionListDto
import com.android.recipe.domain.RecipeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    @TypeConverter
    fun fromRecipeInfoList(recipes: List<RecipeEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<RecipeEntity>>() {}.type
        return gson.toJson(recipes, type)
    }

    @TypeConverter
    fun toRecipeInfo(recipes: String): List<RecipeEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<RecipeEntity>>() {}.type
        return gson.fromJson(recipes, type)
    }
}