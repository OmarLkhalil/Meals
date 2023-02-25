package com.example.domain.entity


data class FavoriteMealCollection(
    val id: String? = null,
    val userId: String? = null,
    val meal: FavoriteMeal? = null
) {

    companion object {
        const val COLLECTION_NAME = "favorite_meals"
    }
}
