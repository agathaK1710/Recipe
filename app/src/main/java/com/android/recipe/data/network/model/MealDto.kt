package com.android.recipe.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("calories")
    @Expose
    val calories: Int,
    @SerializedName("carbs")
    @Expose
    val carbs: String,
    @SerializedName("fat")
    @Expose
    val fat: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("protein")
    @Expose
    val protein: String
)