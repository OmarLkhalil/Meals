package com.example.data.repo

import androidx.lifecycle.liveData
import com.example.data.remote.ApiService
import com.example.domain.repo.DetailsRepo
import com.example.domain.util.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers

@ActivityRetainedScoped
class DetailsRepImpl(private val apiService: ApiService): DetailsRepo {

    override suspend fun getDetailsResult(mealName: String?) = liveData(Dispatchers.IO) {
        emit(Resources.loading(null))
        try {
            val meal= mealName?.let { apiService.getMealDetails(it).body() }
            emit(Resources.success(meal!!.meals))
        } catch (exception: Exception) {
            emit(Resources.failed(null, exception.message.toString()))
        }
    }
}