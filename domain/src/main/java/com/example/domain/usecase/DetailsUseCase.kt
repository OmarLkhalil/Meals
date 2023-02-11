package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entity.MealsItem
import com.example.domain.repo.DetailsRepo
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow

class DetailsUseCase(private val detailsRepo: DetailsRepo) {
    operator fun invoke(mealName: String?): Flow<Resources<List<MealsItem?>?>> = detailsRepo.getDetailsResult(mealName)
}