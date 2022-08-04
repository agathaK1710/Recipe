package com.android.recipe.presentation.adapters

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.recipe.R
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
            servingsNum.text = item.servings.toString()
            readyTime.text = item.readyInMinutes.toString()
            Picasso.get().load(item.image).into(image)
            arrowBtn.setOnClickListener {
                if(invisibleContainer.visibility == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(
                        cardView,
                        AutoTransition()
                    )
                    invisibleContainer.visibility = View.GONE
                    arrowBtn.setImageResource(R.drawable.arrow_more)
                } else {
                    TransitionManager.beginDelayedTransition(
                        cardView,
                        AutoTransition()
                    )
                    invisibleContainer.visibility = View.VISIBLE
                    arrowBtn.setImageResource(R.drawable.arrow_less)
                }
            }
            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }
}