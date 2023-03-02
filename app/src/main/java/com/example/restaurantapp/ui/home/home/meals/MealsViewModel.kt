package com.example.restaurantapp.ui.home.home.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.domain.entity.Meals
import com.example.domain.usecase.MealUseCase


import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val getMealsUseCase: MealUseCase) : ViewModel() {

    private val meals: MutableStateFlow<Meals?> = MutableStateFlow(null)
    val _meals: StateFlow<Meals?> = meals

    fun getMeals(){
        viewModelScope.launch{
            try {
                meals.value = getMealsUseCase()
            } catch (_:java.lang.Exception){

            }
        }
    }
}