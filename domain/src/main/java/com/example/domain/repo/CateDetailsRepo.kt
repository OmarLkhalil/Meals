package com.example.domain.repo

import com.example.domain.util.Resources
import com.restaurantapp.domain.entity.CategoriesItem
import kotlinx.coroutines.flow.Flow

interface CateDetailsRepo {
    fun getCateDetails(cateName: String?) : Flow<Resources<List<CategoriesItem?>?>>
}