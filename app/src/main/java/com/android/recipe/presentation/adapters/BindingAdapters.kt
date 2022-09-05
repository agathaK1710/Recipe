package com.android.recipe.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.recipe.R
import com.squareup.picasso.Picasso

@BindingAdapter("prepareTime")
fun bindPrepareTime(textView: TextView, min: Int) {
    "$min ${textView.context.getString(R.string.min)}".also { textView.text = it }
}

@BindingAdapter("energy")
fun bindEnergy(textView: TextView, kcal: Double) {
    "${kcal.toInt()} ${textView.context.getString(R.string.kcal)}".also { textView.text = it }
}

@BindingAdapter("rightImage")
fun bindHeartImage(imageView: ImageView, favourite: Boolean) {
    imageView.setImageResource(getHeartResId(favourite))
}

private fun getHeartResId(favourite: Boolean): Int {
    return if (favourite) {
        R.drawable.red_heart
    } else {
        R.drawable.heart
    }
}

@BindingAdapter("imageByUrl")
fun bindRecipeImage(imageView: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(imageView)
}


