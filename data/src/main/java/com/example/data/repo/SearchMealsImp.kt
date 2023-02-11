package com.example.data.repo


import androidx.lifecycle.liveData
import com.example.data.remote.ApiService
import com.example.domain.entity.MealsItem
import com.example.domain.util.Resources
import com.example.domain.repo.SearchRepo
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityRetainedScoped
class SearchMealsImp(private val apiService: ApiService) : SearchRepo {

    override  fun getSearchResult(mealName: String?): Flow<Resources<List<MealsItem?>?>> = flow {
        emit(Resources.loading(null))
        try {
            val data = mealName?.let { apiService.getSearchedMeals(it).body()?.meals }
            emit(Resources.success(data))
        } catch (exception: Exception) {
            emit(Resources.failed(data = null, exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


}