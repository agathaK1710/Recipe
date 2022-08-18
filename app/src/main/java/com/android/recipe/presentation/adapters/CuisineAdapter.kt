package com.android.recipe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.R
import com.android.recipe.databinding.CuisineItemBinding
import com.android.recipe.presentation.fragments.Cuisine

class CuisineAdapter(private val cuisines: List<Cuisine>) :
    RecyclerView.Adapter<CuisineViewHolder>() {
    var onClickListener: ((Cuisine) -> Unit)? = null
    private var index = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val view = CuisineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CuisineViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        val cuisine = cuisines[position]
        with(holder) {
            icon.setImageResource(cuisine.image)
            title.text = cuisine.name
            icon.setOnClickListener {
                index = adapterPosition
                cuisine.isClicked = true
                onClickListener?.invoke(cuisine)
                notifyDataSetChanged()
            }
            if (index == position && cuisine.isClicked) {
                setViewColors(R.color.blue)
            } else {
                cuisine.isClicked = false
                setViewColors(R.color.dark_grey)
            }
        }
    }

    private fun CuisineViewHolder.setViewColors(color: Int) {
        title.setTextColor(ContextCompat.getColor(title.context, color))
        DrawableCompat.setTint(
            icon.drawable.mutate(),
            ContextCompat.getColor(icon.context, color)
        )
    }

    override fun getItemCount() = cuisines.size
}