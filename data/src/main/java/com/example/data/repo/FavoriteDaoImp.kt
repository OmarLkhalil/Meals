package com.example.data.repo

import com.example.domain.entity.FavoriteMeal
import com.example.domain.dao.FavoriteMealDao
import com.example.domain.repo.FavoriteMealsRepo
import com.example.domain.util.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception

@ActivityRetainedScoped
class FavoriteDaoImp( private val dao: FavoriteMealDao) : FavoriteMealsRepo {

    override suspend fun addFavoriteMeal(mealId: FavoriteMeal): Flow<Resources<Unit>> = flow {
        emit(Resources.loading())
        try {
            dao.insert(FavoriteMeal())
            emit(Resources.success(Unit))
        } catch (exception: Exception) {
            emit(Resources.failed(exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun removeFavoriteMeal(mealId: String) {
        dao.delete(mealId)
    }

    override suspend fun getFavoriteMeals(): Flow<Resources<List<FavoriteMeal>>> = flow{
        emit(Resources.loading())
        try {
            val favoritesList = dao.getAll().first()
            emit(Resources.success(favoritesList))
        } catch (exception: Exception) {
            emit(Resources.failed(exception.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}