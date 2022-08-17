package com.android.recipe.di

import androidx.lifecycle.ViewModel
import com.android.recipe.presentation.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    fun bindViewModule(viewModel: RecipeViewModel): ViewModel
}