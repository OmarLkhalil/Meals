package com.example.domain.usecase

import com.example.domain.repo.MealRepo

class MealUseCase(private val mealRepo: MealRepo) {
   suspend operator fun  invoke() = mealRepo.getMealsFromApi()
}