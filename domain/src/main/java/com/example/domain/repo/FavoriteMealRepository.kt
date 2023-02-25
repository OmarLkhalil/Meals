package com.example.domain.repo

import androidx.lifecycle.LiveData
import com.example.domain.entity.FavoriteMeal
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

interface FavoriteMealRepository {

    suspend fun addFavoriteMeal(favoriteMeal: FavoriteMeal, userId: String, onSuccessListener: OnSuccessListener<Void>,
                                onFailureListener: OnFailureListener)

    suspend fun deleteFavoriteMeal(favoriteMeal: FavoriteMeal, userId: String ,
                                   onSuccessListener: OnSuccessListener<Void>,
                                   onFailureListener: OnFailureListener)

    suspend fun getFavoriteMeals(userId: String) : LiveData<List<FavoriteMeal>>

}