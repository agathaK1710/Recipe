package com.android.recipe.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.databinding.IngredientItemBinding

class DetailViewHolder(itemView: IngredientItemBinding): RecyclerView.ViewHolder(itemView.root) {
    val image = itemView.ivIngredient
    val title = itemView.tvIngredientName
    val count = itemView.tvIngredientCount
    val unit = itemView.tvUnit
}