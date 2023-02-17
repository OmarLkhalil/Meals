package com.example.restaurantapp.dependecyInjection

import android.content.Context
import com.example.data.local.FavoritesDataBase
import com.example.domain.dao.FavoriteMealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FavoritesDataBase {
        return FavoritesDataBase.getInstance(context)
    }

    @Provides
    fun provideFavoriteMealDao(database: FavoritesDataBase): FavoriteMealDao {
        return database.favoriteMealDao()
    }

}
