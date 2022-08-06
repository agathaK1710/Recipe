package com.android.recipe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.recipe.databinding.RecipeItemBinding
import com.android.recipe.domain.entities.RecipeInfo
import com.squareup.picasso.Picasso


class RecipeAdapter : ListAdapter<RecipeInfo, RecipeViewHolder>(RecipeInfoDiffCallBack()) {
    var onClickListener: ((RecipeInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = RecipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            title.text = item.title
            likes.text = item.likes.toString()
            Picasso.get().load(item.image).into(image)
            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }
}