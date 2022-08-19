package com.android.recipe.di

import android.app.Application
import com.android.recipe.presentation.MainActivity
import com.android.recipe.presentation.fragments.*
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
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}