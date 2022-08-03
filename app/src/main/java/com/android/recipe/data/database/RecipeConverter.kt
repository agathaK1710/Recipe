package com.android.recipe.data.database

import androidx.room.TypeConverter
import com.android.recipe.data.database.entities.RecipeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    @TypeConverter
    fun fromIngredientIdList(ids: List<String>): String {
        return ids.joinToString(", ")
    }

    @TypeConverter
    fun toIngredientIdList(ids: String): List<String> {
       return ids.split(", ")
    }
}