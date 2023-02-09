package com.example.domain.repo

import androidx.lifecycle.LiveData
import com.example.domain.entity.MealsItem
import com.example.domain.util.Resources

interface DetailsRepo {
    suspend fun getDetailsResult(mealName: String?) : LiveData<Resources<List<MealsItem?>?>>
}