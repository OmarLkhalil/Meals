package com.example.restaurantapp.ui.main.detailsmeal

import androidx.lifecycle.ViewModel


import com.example.domain.entity.FavoriteMeal
import com.example.domain.repo.FavoriteMealRepository
import com.example.domain.repo.MealDetailsRepo


import com.example.restaurantapp.DataUtils


import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener


import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMealViewModel @Inject constructor(
     private val mealDetailsRepo: MealDetailsRepo,
     private val favoriteMealsRepo: FavoriteMealRepository,
) : ViewModel() {

     private val userId: String = DataUtils.user!!.id.toString()

     fun getMealsDetails(mealId: String) = mealDetailsRepo.getDetailsResult(mealId)

     suspend fun toggleFavoriteMeal(favoriteMeal: FavoriteMeal, onSuccessListener: OnSuccessListener<Void>, onFailureListener: OnFailureListener) {
          if (favoriteMeal.isFavored) {
               favoriteMealsRepo.deleteFavoriteMeal(favoriteMeal, userId, onSuccessListener, onFailureListener)
          } else {
               favoriteMealsRepo.addFavoriteMeal(favoriteMeal, userId, onSuccessListener, onFailureListener)
          }
     }
}