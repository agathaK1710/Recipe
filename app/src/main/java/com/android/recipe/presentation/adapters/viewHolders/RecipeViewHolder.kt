package com.android.recipe.presentation.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.databinding.RecipeItemBinding

class RecipeViewHolder(itemView: RecipeItemBinding): RecyclerView.ViewHolder(itemView.root){
    val image = itemView.ivRecipe
    val title = itemView.tvTitle
    val likes = itemView.tvLikes
    //val healthScore = itemView.pbHealthScore
}