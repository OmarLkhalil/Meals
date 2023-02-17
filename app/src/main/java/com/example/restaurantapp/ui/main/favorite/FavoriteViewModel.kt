package com.example.restaurantapp.ui.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.FavoriteMeal
import com.example.domain.repo.FavoriteMealsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteMealsRepo: FavoriteMealsRepo): ViewModel() {

    suspend fun addMealToFavorite(meal: FavoriteMeal)= favoriteMealsRepo.addFavoriteMeal(meal)

    suspend fun removeMealFromFavorite(mealId: String){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteMealsRepo.removeFavoriteMeal(mealId)
        }
    }

    suspend fun getAllFavorites() = favoriteMealsRepo.getFavoriteMeals()
}