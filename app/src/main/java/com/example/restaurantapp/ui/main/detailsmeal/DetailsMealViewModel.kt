package com.example.restaurantapp.ui.main.detailsmeal

import androidx.lifecycle.ViewModel
import com.example.domain.repo.MealDetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMealViewModel @Inject constructor(private val mealDetailsRepo: MealDetailsRepo): ViewModel() {
     fun getMealsDetails(mealId:String) = mealDetailsRepo.getDetailsResult(mealId)

}