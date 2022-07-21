package com.android.recipe.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.recipe.domain.entities.RecipeInfo

class RecipeInfoDiffCallBack: DiffUtil.ItemCallback<RecipeInfo>() {
    override fun areItemsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo): Boolean {
        return oldItem == newItem
    }
}