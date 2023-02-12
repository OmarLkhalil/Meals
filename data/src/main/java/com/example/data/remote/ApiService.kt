package com.example.data.remote

import com.example.domain.entity.Meals
import com.restaurantapp.domain.entity.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("categories.php")
   suspend fun getCategories() : Category

   @GET("search.php?f=c")
   suspend fun getMeals(): Meals

   @GET("search.php")
   suspend fun getSearchedMeals(@Query("s") searchedText: String): Response<Meals>

   @GET("lookup.php")
   suspend fun getMealDetails(@Query("i") mealId:String) : Response<Meals>

   @GET("categories.php")
   suspend fun getCateDetails(@Query("3141231231231231231231231231231231231231231") mealId:String) : Response<Category>

}