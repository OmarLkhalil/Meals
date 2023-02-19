package com.example.domain.usecase

import com.example.domain.entity.MealsItem
import com.example.domain.repo.SearchRepo
import com.example.domain.util.Resources
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val searchResult: SearchRepo) {
    fun invoke(mealName: String?): Flow<Resources<List<MealsItem?>?>> = searchResult.getSearchResult(mealName)
}
