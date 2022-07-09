package com.android.recipe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.recipe.data.network.model.RecipeDto

@Database(version = 1, entities = [RecipeDto::class], exportSchema = false)
@TypeConverters(RecipeConverter::class)
abstract class AppDatabase: RoomDatabase(){
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "app.db"

        fun getInstance(context: Context): AppDatabase{
            db?.let {
                return it
            }
            val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            db = instance
            return instance
        }
    }

    abstract fun recipeDao(): RecipeInfoDao
}