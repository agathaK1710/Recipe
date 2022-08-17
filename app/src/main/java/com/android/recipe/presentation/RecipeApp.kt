package com.android.recipe.presentation

import android.app.Application
import com.android.recipe.di.DaggerApplicationComponent

class RecipeApp: Application() {
    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}