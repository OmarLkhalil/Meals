package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entity.MealsItem
import com.example.domain.repo.DetailsRepo
import com.example.domain.util.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityRetainedScoped
class DetailsRepImpl(private val apiService: ApiService): DetailsRepo {

    override fun getDetailsResult(mealName: String?): Flow<Resources<List<MealsItem?>?>> = flow {
        emit(Resources.loading(null))
        try {
            val meal= mealName?.let { apiService.getMealDetails(it).body() }
            emit(Resources.success(meal!!.meals))
        } catch (exception: Exception) {
            emit(Resources.failed(null, exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}