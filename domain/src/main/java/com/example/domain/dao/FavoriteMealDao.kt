package com.example.domain.dao

import androidx.room.*
import com.example.domain.entity.FavoriteMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMealDao {

    @Query("SELECT * FROM FavoriteMeals")
    fun getAll(): Flow<List<FavoriteMeal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: FavoriteMeal)

    @Query("DELETE FROM FavoriteMeals WHERE meal_id like :meal")
    fun delete(meal: String)

}