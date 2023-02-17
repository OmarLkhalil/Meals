package com.example.domain.usecase

import com.example.domain.entity.MealsItem
import com.example.domain.repo.MealDetailsRepo
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow

class DetailsUseCase(private val mealDetailsRepo: MealDetailsRepo) {
    operator fun invoke(mealName: String?): Flow<Resources<List<MealsItem?>?>> = mealDetailsRepo.getDetailsResult(mealName)
}