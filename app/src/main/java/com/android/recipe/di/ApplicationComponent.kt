package com.android.recipe.di

import android.app.Application
import com.android.recipe.presentation.MainActivity
import com.android.recipe.presentation.fragments.FavouritesFragment
import com.android.recipe.presentation.fragments.RecipeDetailFragment
import com.android.recipe.presentation.fragments.RecipesListFragment
import com.android.recipe.presentation.fragments.StepFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: StepFragment)
    fun inject(fragment: RecipeDetailFragment)
    fun inject(fragment: RecipesListFragment)
    fun inject(fragment: FavouritesFragment)

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}