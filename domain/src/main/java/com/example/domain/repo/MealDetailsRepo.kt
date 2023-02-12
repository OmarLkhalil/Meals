package com.example.domain.repo

import com.example.domain.entity.MealsItem
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow

interface MealDetailsRepo {
    fun getDetailsResult(mealName: String?) : Flow<Resources<List<MealsItem?>?>>
}