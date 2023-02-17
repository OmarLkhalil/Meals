package com.example.domain.repo

import com.example.domain.entity.FavoriteMeal
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow


interface FavoriteMealsRepo {
    suspend fun addFavoriteMeal(mealId: FavoriteMeal) : Flow<Resources<Unit>>
    suspend fun removeFavoriteMeal(mealId: String)
    suspend fun getFavoriteMeals(): Flow<Resources<List<FavoriteMeal>>>
}