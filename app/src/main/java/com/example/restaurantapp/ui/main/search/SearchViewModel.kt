package com.example.restaurantapp.ui.main.search

import androidx.lifecycle.*
import com.example.domain.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {
     fun getSearchedMeals(searchedTxt:String) = searchRepo.getSearchResult(searchedTxt)
}
