package com.example.restaurantapp.dependecyInjection

import com.example.domain.dao.FavoriteMealDao
import com.example.domain.repo.*
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// this module provides a "CateUseCase" object by taking a "CateRepo"
// object as a parameter and using it to create a new instance of "CateUseCase".
// The module is intended to be installed in a singleton component.
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun getUseCase(cateRepo: CateRepo): CateUseCase {
        return CateUseCase(cateRepo)
    }

    @Provides
    fun getMealUseCase(mealRepo: MealRepo): MealUseCase {
        return MealUseCase(mealRepo)
    }

    @Provides
    fun getSearchUseCase(searchRepo: SearchRepo): SearchUseCase {
        return SearchUseCase(searchRepo)
    }

    @Provides
    fun getDetailUseCase(detailRepo: MealDetailsRepo): DetailsUseCase{
        return DetailsUseCase(detailRepo)
    }

    @Provides
    fun getCateDetailUseCase(detailRepo: FilterByCateRepo): CateDetailsUseCase{
        return CateDetailsUseCase(detailRepo)
    }

    @Provides
    fun getFavoritesUseCase(favoritesDao: FavoriteMealDao): FavoriteUseCase {
        return FavoriteUseCase(favoritesDao)
    }
}