package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.entity.FavoriteMeal
import com.example.domain.dao.FavoriteMealDao

@Database(entities = [FavoriteMeal::class], version = 1)
abstract class FavoritesDataBase : RoomDatabase() {
    abstract fun favoriteMealDao(): FavoriteMealDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritesDataBase? = null

        fun getInstance(context: Context): FavoritesDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
