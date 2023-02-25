package com.example.restaurantapp.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.FavoriteMeal
import com.example.domain.repo.FavoriteMealRepository
import com.example.restaurantapp.DataUtils
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMealsViewModel @Inject constructor(
    private val repository: FavoriteMealRepository
) : ViewModel() {


    suspend fun removeFavoriteMeal(
        favoriteMeal: FavoriteMeal,
        userId: String,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        repository.deleteFavoriteMeal(favoriteMeal, userId, onSuccessListener, onFailureListener)
    }

    var userId =  DataUtils.user!!.id.toString()

    suspend fun getFavoriteMeals(): LiveData<List<FavoriteMeal>> {
        return repository.getFavoriteMeals(userId)
    }
}