package com.example.restaurantapp.ui.main.detailscategory

import androidx.lifecycle.ViewModel
import com.example.domain.repo.FilterByCateRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsCategoryViewModel @Inject constructor(private val detailsRepo: FilterByCateRepo): ViewModel() {
     fun getCateDetails(cateId:String) = detailsRepo.getCateDetails(cateId)
}