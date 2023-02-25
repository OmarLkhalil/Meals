package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteMeal(

    val id: Int? =null,

    val idMeal: String? =null,

    val strMeal: String? =null,

    val strMealThumb: String? =null,

    var isFavored:Boolean = false

): Parcelable