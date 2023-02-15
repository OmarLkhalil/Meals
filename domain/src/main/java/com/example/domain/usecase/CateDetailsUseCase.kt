package com.example.domain.usecase

import com.example.domain.entity.MealsItem
import com.example.domain.repo.FilterByCateRepo
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow

class CateDetailsUseCase(private val cateDetailsRepo: FilterByCateRepo) {
    operator fun invoke(cateName: String?): Flow<Resources<List<MealsItem?>?>> = cateDetailsRepo.getCateDetails(cateName)
}