package com.android.recipe.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.recipe.domain.entities.IngredientInfo
import com.android.recipe.presentation.Ingredient

class IngredientDiffCallback: DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}