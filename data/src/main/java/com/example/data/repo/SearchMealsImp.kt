package com.example.data.repo


import androidx.lifecycle.liveData
import com.example.data.remote.ApiService
import com.example.domain.util.Resources
import com.example.domain.repo.SearchRepo
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers

@ActivityRetainedScoped
class SearchMealsImp(private val apiService: ApiService) : SearchRepo {

    override suspend fun getSearchResult(mealName: String?) = liveData(Dispatchers.IO) {
        emit(Resources.loading(null))
        try {
            val data = mealName?.let { apiService.getSearchedMeals(it).body()?.meals }
            emit(Resources.success(data))

        } catch (exception: Exception) {
            Resources.failed(data = null, exception.message.toString())
        }
    }

}