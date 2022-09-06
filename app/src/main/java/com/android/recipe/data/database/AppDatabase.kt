package com.android.recipe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.recipe.data.database.entities.*

@Database(
    version = 3,
    entities = [
        IngredientEntity::class,
        RecipeEntity::class,
        StepEntity::class,
        RecipeIngredientRatio::class,
        StepIngredientRatio::class,
        UserEntity::class
    ],
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "recipeApp.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao
}