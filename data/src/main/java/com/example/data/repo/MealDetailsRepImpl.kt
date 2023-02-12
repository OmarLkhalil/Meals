package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entity.MealsItem
import com.example.domain.repo.MealDetailsRepo
import com.example.domain.util.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityRetainedScoped
class MealDetailsRepImpl(private val apiService: ApiService): MealDetailsRepo {

    override fun getDetailsResult(mealName: String?): Flow<Resources<List<MealsItem?>?>> = flow {
        emit(Resources.loading())
        try {
            val meal= mealName?.let { apiService.getMealDetails(it).body() }
            emit(Resources.success(meal!!.meals))
        } catch (exception: Exception) {
            emit(Resources.failed(exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}