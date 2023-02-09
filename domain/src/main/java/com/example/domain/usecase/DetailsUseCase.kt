package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entity.MealsItem
import com.example.domain.repo.DetailsRepo
import com.example.domain.util.Resources

class DetailsUseCase(private val detailsRepo: DetailsRepo) {
    suspend operator fun invoke(mealName: String?): LiveData<Resources<List<MealsItem?>?>> = detailsRepo.getDetailsResult(mealName)
}