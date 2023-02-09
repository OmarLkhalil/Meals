package com.example.restaurantapp.ui.detailsmeal

import androidx.lifecycle.ViewModel
import com.example.domain.repo.DetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMealViewModel @Inject constructor(private val detailsRepo: DetailsRepo): ViewModel() {
    suspend fun getMealsDetails(mealId:String) = detailsRepo.getDetailsResult(mealId)

}