package com.android.recipe.data.network

import com.android.recipe.data.network.model.RecipeDto
import com.android.recipe.data.network.model.RecipeListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    fun searchRecipeByName(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_NAME) name: String
    ): Single<RecipeListDto>

    @GET("findByIngredients")
    fun searchRecipeByIngredients(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_INGREDIENTS) ingredients: String
    ): List<RecipeDto>

    companion object{
        private const val API_KEY = "f1d443bb4cba44de9aa1ee2855f95135"
        private const val QUERY_PARAM_API_KEY= "apiKey"
        private const val QUERY_PARAM_NAME= "query"
        private const val QUERY_PARAM_INGREDIENTS= "ingredients"
    }
}