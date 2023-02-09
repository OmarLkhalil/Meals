package com.example.domain.repo

import com.restaurantapp.domain.entity.Category

interface CateRepo {
    suspend fun getCategoriesFromApi(): Category
}