package com.example.data.remote

import com.example.domain.entity.Meals
import com.restaurantapp.domain.entity.Category
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("categories.php")
   suspend fun getCategories() : Category

   @GET("search.php?f=b")
   suspend fun getMeals(): Meals

   @GET("search.php")
   suspend fun getSearchedMeals(@Query("s") searchedText: String): Response<Meals>

   @GET("lookup.php")
   suspend fun getMealDetails(@Query("i") mealId:String) : Response<Meals>

}