package com.example.restaurantapp.dependecyInjection

import com.example.data.remote.ApiService
import com.example.data.repo.*
import com.example.domain.repo.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// this module provides a "CateRepo" object by taking an "ApiService" object
// as a parameter and using it to create a new instance of "CategoriesRepImp".
// The module is intended to be installed in a singleton component.
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService): CateRepo {
        return CategoriesRepImp(apiService)
    }

    @Provides
    fun provideMealRepo(apiService: ApiService): MealRepo {
        return MealsRepImp(apiService)
    }

    @Provides
    fun provideSearchRepo(apiService: ApiService): SearchRepo{
        return SearchMealsImp(apiService)
    }

    @Provides
    fun provideDetailsRepo(apiService: ApiService): MealDetailsRepo{
        return MealDetailsRepImpl(apiService)
    }

    @Provides
    fun provideCateDetailsRepo(apiService: ApiService): FilterByCateRepo{
        return CategoriesImplFilterBy(apiService)
    }
}