package com.android.recipe.data.network

import com.android.recipe.data.network.model.MealDto
import com.android.recipe.data.network.model.RecipeDto
import com.android.recipe.data.network.model.RecipeListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    fun searchRecipeByName(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_NAME) name: String,
        @Query(QUERY_PARAM_MAX_FAT) maxFat: Int = 25
    ): Single<RecipeListDto>

    @GET("findByIngredients")
    fun searchRecipeByIngredients(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_INGREDIENTS) ingredients: String
    ): Single<List<RecipeDto>>

    companion object{
        private const val API_KEY = "69d6f243b5f44ddd9852101ec7c475c1"
        private const val QUERY_PARAM_API_KEY= "apiKey"
        private const val QUERY_PARAM_NAME= "query"
        private const val QUERY_PARAM_MAX_FAT= "maxFat"
        private const val QUERY_PARAM_INGREDIENTS= "ingredients"
    }
}