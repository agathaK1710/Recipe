package com.android.recipe.data.network.model

data class NutritionListDto(
    val nutrients: List<NutrientDto>
) {
    fun getNutrientAmount(
        nutrition: Nutrition
    ) = nutrients.find { nutrition.name == it.name }?.amount ?: 0.0


    enum class Nutrition(name: String) {
        CALORIES("Calories"),
        FAT("Fat"),
        PROTEIN("Protein"),
        CARBOHYDRATES("Carbohydrates")
    }
}