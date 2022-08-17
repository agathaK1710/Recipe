package com.android.recipe.di

import android.app.Application
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.database.RecipeDao
import com.android.recipe.data.repository.RecipeRepositoryImpl
import com.android.recipe.domain.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    companion object {
        @Provides
        fun provideRecipeDao(
            application: Application
        ): RecipeDao {
            return AppDatabase.getInstance(application).recipeDao()
        }
    }
}