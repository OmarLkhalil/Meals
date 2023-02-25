package com.example.restaurantapp.ui.main.home.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.restaurantapp.domain.entity.Category


import com.example.domain.usecase.CateUseCase


import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class CateViewModel @Inject constructor(private val getCategoriesUseCase: CateUseCase): ViewModel(){

    private val categories: MutableStateFlow<Category?> = MutableStateFlow(null)
    val _categories: StateFlow<Category?> = categories


    fun getCategories(){
        viewModelScope.launch{
         try {
             categories.value = getCategoriesUseCase()
         } catch (_:java.lang.Exception){

            }
        }
    }
}