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
    suspend fun searchRecipeByCuisine(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_CUISINE) cuisine: String
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
        private const val API_KEY = "dd0a22d093894fa9a2496d8fdba0ffbf"
        private const val QUERY_PARAM_API_KEY= "apiKey"
        private const val QUERY_PARAM_NAME= "query"
        private const val QUERY_PARAM_INGREDIENTS= "ingredients"
        private const val QUERY_PARAM_NUMBER = "number"
        private const val QUERY_PARAM_ID = "id"
        private const val QUERY_PARAM_NUTRITION ="includeNutrition"
        private const val QUERY_PARAM_CUISINE="cuisine"
    }
}