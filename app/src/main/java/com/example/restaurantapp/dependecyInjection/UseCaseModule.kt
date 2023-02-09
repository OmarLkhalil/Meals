package com.example.restaurantapp.dependecyInjection

import com.example.domain.repo.CateRepo
import com.example.domain.repo.MealRepo
import com.example.domain.repo.SearchRepo
import com.example.domain.usecase.CateUseCase
import com.example.domain.usecase.MealUseCase
import com.example.domain.usecase.SearchUseCase
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
}