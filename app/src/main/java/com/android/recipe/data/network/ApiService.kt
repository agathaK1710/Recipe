package com.android.recipe.data.network

import com.android.recipe.data.network.model.RecipeDetailDto
import com.android.recipe.data.network.model.RecipeDto
import com.android.recipe.data.network.model.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    suspend fun searchRecipeByName(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_NAME) name: String,
        @Query(QUERY_PARAM_MAX_CALORIES) maxCalories: Int = MAX_ENERGY_VALUE,
        @Query(QUERY_PARAM_MAX_PROTEIN) maxProtein: Int = MAX_ENERGY_VALUE,
        @Query(QUERY_PARAM_MAX_FAT) maxFat: Int = MAX_ENERGY_VALUE,
        @Query(QUERY_PARAM_MAX_CARBS) maxCarbs: Int = MAX_ENERGY_VALUE
    ): RecipeListDto

    @GET("findByIngredients")
    suspend fun searchRecipeByIngredients(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_INGREDIENTS) ingredients: String
    ): List<RecipeDto>

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path(QUERY_PARAM_ID) id: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): RecipeDetailDto

    companion object{
        private const val API_KEY = "69d6f243b5f44ddd9852101ec7c475c1"
        private const val MAX_ENERGY_VALUE = 10000
        private const val QUERY_PARAM_API_KEY= "apiKey"
        private const val QUERY_PARAM_NAME= "query"
        private const val QUERY_PARAM_MAX_CALORIES= "maxCalories"
        private const val QUERY_PARAM_MAX_PROTEIN= "maxProtein"
        private const val QUERY_PARAM_MAX_FAT= "maxFat"
        private const val QUERY_PARAM_MAX_CARBS= "maxCarbs"
        private const val QUERY_PARAM_INGREDIENTS= "ingredients"
        private const val QUERY_PARAM_ID = "id"
    }
}