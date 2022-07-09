package com.android.recipe.data.database

import androidx.room.TypeConverter
import com.android.recipe.data.network.model.IngredientDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    @TypeConverter
    fun fromIngredientsList(ingredients: List<IngredientDto>): String {
        val gson = Gson()
        val type = object : TypeToken<List<IngredientDto>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientsList(ingredients: String): List<IngredientDto> {
        val gson = Gson()
        val type = object : TypeToken<List<IngredientDto>>() {}.type
        return gson.fromJson(ingredients, type)
    }
}