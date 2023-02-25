package com.example.domain.entity

data class AppUser(
    val id: String?= null,
    val name: String?= null,
    val email: String?= null,
    val favoriteMealId: String? = null,
    ) {

    companion object{
        const val COLLECTION_NAME = "users"
    }
}