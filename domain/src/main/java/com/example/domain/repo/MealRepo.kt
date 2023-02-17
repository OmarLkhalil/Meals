package com.example.domain.repo

import com.example.domain.entity.Meals

interface MealRepo {
    suspend fun getMealsFromApi(): Meals
}