package com.android.recipe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.recipe.databinding.IngredientItemBinding
import com.android.recipe.presentation.Ingredient
import com.squareup.picasso.Picasso

class RecipeDetailAdapter() :
    RecyclerView.Adapter<DetailViewHolder>() {
    var ingredients: List<Ingredient> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val ingredient = ingredients[position]
        with(holder) {
            title.text = ingredient.name
            Picasso.get().load(ingredient.image).into(image)
            count.text = ingredient.amount.toString()
            unit.text = ingredient.unit
        }
    }

    override fun getItemCount() = ingredients.size
}