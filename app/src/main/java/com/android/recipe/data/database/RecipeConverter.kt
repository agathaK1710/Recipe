package com.android.recipe.data.database

import androidx.room.TypeConverter
import com.android.recipe.data.network.model.EquipmentDto
import com.android.recipe.data.network.model.IngredientDto
import com.android.recipe.data.network.model.NutrientDto
import com.android.recipe.data.network.model.NutritionListDto
import com.android.recipe.domain.RecipeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    @TypeConverter
    fun fromRecipeInfo(recipeInfo: RecipeInfo): String {
        val gson = Gson()
        val type = object : TypeToken<RecipeInfo>() {}.type
        return gson.toJson(recipeInfo, type)
    }

    @TypeConverter
    fun toRecipeInfo(recipeInfo: String): RecipeInfo {
        val gson = Gson()
        val type = object : TypeToken<RecipeInfo>() {}.type
        return gson.fromJson(recipeInfo, type)
    }
}