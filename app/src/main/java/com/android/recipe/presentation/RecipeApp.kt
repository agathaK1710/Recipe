package com.android.recipe.presentation

import android.app.Application
import com.android.recipe.di.DaggerApplicationComponent
import com.google.firebase.ktx.Firebase

class RecipeApp: Application() {
    val component by lazy{
        DaggerApplicationComponent.factory().create(this, Firebase)
    }
}