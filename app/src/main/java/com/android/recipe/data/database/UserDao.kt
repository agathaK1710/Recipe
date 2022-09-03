package com.android.recipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.recipe.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id == :id")
    fun getUser(id: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(user: UserEntity)
}