package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entity.MealsItem
import com.example.domain.repo.FilterByCateRepo
import com.example.domain.util.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityRetainedScoped
class CategoriesImplFilterBy(private val apiService: ApiService): FilterByCateRepo {

    override fun getCateDetails(cateName: String?): Flow<Resources<List<MealsItem?>?>> = flow {
        emit(Resources.loading())
        try {
            val cate= cateName?.let { apiService.getMealsByCategory(it).body() }
            emit(Resources.success(cate!!.meals))
        } catch (exception: Exception) {
            emit(Resources.failed(exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}