package com.example.data.repo

import com.example.data.remote.ApiService
import com.restaurantapp.domain.entity.Category
import com.example.domain.repo.CateRepo

class CategoriesRepImp(private val apiService: ApiService) : CateRepo
{
    override suspend fun getCategoriesFromApi(): Category = apiService.getCategories()
}