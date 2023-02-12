package com.example.domain.usecase

import com.example.domain.repo.CateDetailsRepo
import com.example.domain.util.Resources
import com.restaurantapp.domain.entity.CategoriesItem
import kotlinx.coroutines.flow.Flow

class CateDetailsUseCase(private val cateDetailsRepo: CateDetailsRepo) {
    operator fun invoke(cateName: String?): Flow<Resources<List<CategoriesItem?>?>> = cateDetailsRepo.getCateDetails(cateName)
}