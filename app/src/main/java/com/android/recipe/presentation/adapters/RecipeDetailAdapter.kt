package com.android.recipe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.recipe.databinding.IngredientItemBinding
import com.android.recipe.presentation.Ingredient
import com.squareup.picasso.Picasso

class RecipeDetailAdapter :
    ListAdapter<Ingredient, DetailViewHolder>(IngredientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val ingredient = getItem(position)
        with(holder) {
            title.text = ingredient.name
            Picasso.get().load(ingredient.image).into(image)
            count.text = ingredient.amount.toString()
            unit.text = ingredient.unit
        }
    }
}