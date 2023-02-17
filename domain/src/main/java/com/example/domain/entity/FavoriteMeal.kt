package com.example.domain.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "FavoriteMeals")
@Parcelize
data class FavoriteMeal(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,

    @ColumnInfo(name = "meal_id")
    val idMeal: String? =null,

    @ColumnInfo(name="meal_name")
    val strMeal: String? =null,

    @ColumnInfo(name = "meal_image")
    val strMealThumb: String? =null,

    @ColumnInfo(name = "isFavored")
    var isFavored:Boolean = false

): Parcelable