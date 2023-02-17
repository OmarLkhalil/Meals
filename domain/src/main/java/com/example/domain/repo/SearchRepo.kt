package com.example.domain.repo

import com.example.domain.entity.MealsItem
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow


interface SearchRepo {
    fun getSearchResult(mealName: String?): Flow<Resources<List<MealsItem?>?>>
}
