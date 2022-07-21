package com.android.recipe.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.databinding.RecipeItemBinding

class RecipeViewHolder(itemView: RecipeItemBinding): RecyclerView.ViewHolder(itemView.root){
    val image = itemView.ivRecipe
    val title = itemView.tvTitle
    val servingsNum = itemView.tvServings
    val readyTime = itemView.tvReadyInMinutes
    //val healthScore = itemView.pbHealthScore
}