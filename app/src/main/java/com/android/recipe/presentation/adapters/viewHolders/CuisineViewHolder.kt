package com.android.recipe.presentation.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.databinding.CuisineItemBinding

class CuisineViewHolder(itemView: CuisineItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    val icon = itemView.ivIcon
    val title = itemView.tvCuisineName
}