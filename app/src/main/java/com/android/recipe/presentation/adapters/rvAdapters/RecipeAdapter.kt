package com.android.recipe.presentation.adapters.rvAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.ListAdapter
import com.android.recipe.databinding.RecipeItemBinding
import com.android.recipe.domain.entities.RecipeInfo
import com.android.recipe.presentation.adapters.RecipeInfoDiffCallBack
import com.android.recipe.presentation.adapters.viewHolders.RecipeViewHolder
import com.squareup.picasso.Picasso


class RecipeAdapter : ListAdapter<RecipeInfo, RecipeViewHolder>(RecipeInfoDiffCallBack()) {
    var onClickListener: ((RecipeInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = RecipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val params: ViewGroup.MarginLayoutParams = view.root.layoutParams as ViewGroup.MarginLayoutParams
        params.width = (parent.width / 2) - parent.marginEnd
        view.root.layoutParams = params
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