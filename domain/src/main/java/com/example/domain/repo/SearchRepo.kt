package com.example.domain.repo

import androidx.lifecycle.LiveData
import com.example.domain.entity.MealsItem
import com.example.domain.util.Resources

interface SearchRepo {
    suspend fun getSearchResult(mealName: String?) : LiveData<Resources<List<MealsItem?>?>>
}
