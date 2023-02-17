package com.example.domain.usecase

import com.example.domain.entity.FavoriteMeal
import com.example.domain.dao.FavoriteMealDao
import kotlinx.coroutines.flow.Flow

class FavoriteUseCase(private val favRepo: FavoriteMealDao) {
    operator fun invoke(): Flow<List<FavoriteMeal>> = favRepo.getAll()
}