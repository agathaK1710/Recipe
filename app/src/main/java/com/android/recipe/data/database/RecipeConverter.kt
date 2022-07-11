package com.android.recipe.data.database

import androidx.room.TypeConverter
import com.android.recipe.data.network.model.EquipmentDto
import com.android.recipe.data.network.model.IngredientDto
import com.android.recipe.data.network.model.NutrientDto
import com.android.recipe.data.network.model.NutritionListDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeConverter {
    @TypeConverter
    fun fromIngredientsList(ingredients: List<IngredientDto>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<IngredientDto>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientsList(ingredients: String): List<IngredientDto>? {
        val gson = Gson()
        val type = object : TypeToken<List<IngredientDto>>() {}.type
        return gson.fromJson(ingredients, type)
    }

    @TypeConverter
    fun fromNutrientList(nutrients: List<NutrientDto>): String {
        val gson = Gson()
        val type = object : TypeToken<List<NutrientDto>>() {}.type
        return gson.toJson(nutrients, type)
    }

    @TypeConverter
    fun toNutrientList(nutrients: String): List<NutrientDto> {
        val gson = Gson()
        val type = object : TypeToken<List<NutrientDto>>() {}.type
        return gson.fromJson(nutrients, type)
    }

    @TypeConverter
    fun fromDishTypesList(dishTypes: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(dishTypes, type)
    }

    @TypeConverter
    fun toDishTypesList(dishTypes: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(dishTypes, type)
    }

    @TypeConverter
    fun fromEquipmentsList(equipments: List<EquipmentDto>): String {
        val gson = Gson()
        val type = object : TypeToken<List<EquipmentDto>>() {}.type
        return gson.toJson(equipments, type)
    }

    @TypeConverter
    fun toEquipmentsList(equipments: String): List<EquipmentDto> {
        val gson = Gson()
        val type = object : TypeToken<List<EquipmentDto>>() {}.type
        return gson.fromJson(equipments, type)
    }
}