package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entity.Meals
import com.example.domain.repo.MealRepo

class MealsRepImp(private val apiService: ApiService) : MealRepo {
    override suspend fun getMealsFromApi(): Meals = apiService.getMeals()
}