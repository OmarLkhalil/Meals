package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entity.MealsItem
import com.example.domain.repo.SearchRepo
import com.example.domain.util.Resources

class SearchUseCase(private val searchResult: SearchRepo) {
    suspend operator fun  invoke(mealName: String?): LiveData<Resources<List<MealsItem?>?>> = searchResult.getSearchResult(mealName)
}
