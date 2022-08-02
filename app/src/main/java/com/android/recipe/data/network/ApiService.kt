package com.android.recipe.data.network

import com.android.recipe.data.network.model.RandomRecipeListDto
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
        @Query(QUERY_PARAM_NAME) name: String
    ): RecipeListDto

    @GET("findByIngredients")
    suspend fun searchRecipeByIngredients(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_INGREDIENTS) ingredients: String
    ): List<RecipeDto>

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path(QUERY_PARAM_ID) id: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_NUTRITION) includeNutrition: Boolean
    ): RecipeDetailDto

    @GET("random")
    suspend fun getRandomRecipes(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_NUMBER) number: Int
    ): RandomRecipeListDto

    companion object{
        private const val API_KEY = "0910672c438e4f91a8ebad23ebe664ac"
        private const val QUERY_PARAM_API_KEY= "apiKey"
        private const val QUERY_PARAM_NAME= "query"
        private const val QUERY_PARAM_INGREDIENTS= "ingredients"
        private const val QUERY_PARAM_NUMBER = "number"
        private const val QUERY_PARAM_ID = "id"
        private const val QUERY_PARAM_NUTRITION ="includeNutrition"
    }
}