package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.repo.CateDetailsRepo
import com.example.domain.util.Resources
import com.restaurantapp.domain.entity.CategoriesItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityRetainedScoped
class CategoriesDetailsImpl(private val apiService: ApiService): CateDetailsRepo {

    override fun getCateDetails(cateName: String?): Flow<Resources<List<CategoriesItem?>?>> = flow {
        emit(Resources.loading())
        try {
            val cate= cateName?.let { apiService.getCateDetails(it).body() }
            emit(Resources.success(cate!!.categories))
        } catch (exception: Exception) {
            emit(Resources.failed(exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}