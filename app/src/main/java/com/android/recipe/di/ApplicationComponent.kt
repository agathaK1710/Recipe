package com.android.recipe.di

import android.app.Application
import com.android.recipe.presentation.MainActivity
import com.android.recipe.presentation.fragments.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class, ViewModelModule::class, PresentationModule::class]
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
    fun inject(fragment: EditFragment)
    fun inject(fragment: DeleteAccountDialog)

    @Component.Factory
    interface ApplicationFactory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance firebase: Firebase
        ): ApplicationComponent
    }

}