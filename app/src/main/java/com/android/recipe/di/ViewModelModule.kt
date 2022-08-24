package com.android.recipe.di

import androidx.lifecycle.ViewModel
import com.android.recipe.presentation.RecipeViewModel
import com.android.recipe.presentation.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    fun bindRecipeViewModule(viewModel: RecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindUserViewModule(viewModel: UserViewModel): ViewModel
}