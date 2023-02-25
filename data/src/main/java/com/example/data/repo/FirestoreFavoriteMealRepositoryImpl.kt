package com.example.data.repo

import android.content.ContentValues.TAG
import android.util.Log


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


import com.example.domain.entity.FavoriteMeal
import com.example.domain.entity.FavoriteMealCollection


import com.example.domain.repo.FavoriteMealRepository


import com.example.domain.util.getCollection


import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


import kotlinx.coroutines.tasks.await

class FirestoreFavoriteMealRepositoryImpl: FavoriteMealRepository {

    private val db = Firebase.firestore

    override suspend fun addFavoriteMeal(favoriteMeal: FavoriteMeal, userId: String,
                                         onSuccessListener: OnSuccessListener<Void>,
                                         onFailureListener: OnFailureListener) {
        val favoriteMealsCollection = getCollection("favorite_meals")
        val favoriteMealDoc = favoriteMealsCollection.document()
        val favoriteMealCollection = FavoriteMealCollection(favoriteMealDoc.id, userId, favoriteMeal)
        favoriteMealDoc.set(favoriteMealCollection)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override suspend fun deleteFavoriteMeal(favoriteMeal: FavoriteMeal, userId: String,
                                            onSuccessListener: OnSuccessListener<Void>,
                                            onFailureListener: OnFailureListener) {
        val favoriteMealsCollection = getCollection("favorite_meals")
        val favoriteMealDoc = favoriteMealsCollection
            .whereEqualTo("userId", userId)
            .whereEqualTo("meal.idMeal", favoriteMeal.idMeal)
            .get()
            .await()
            .documents[0].reference
        favoriteMealDoc.delete()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }


    override suspend fun getFavoriteMeals(userId: String): LiveData<List<FavoriteMeal>> {
        val favoriteMealsCollection = getCollection("favorite_meals")
            .whereEqualTo("userId", userId)

        val result = MutableLiveData<List<FavoriteMeal>>()
        val listenerRegistration = favoriteMealsCollection
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val favoriteMeals = mutableListOf<FavoriteMeal>()
                for (document in querySnapshot!!.documents) {
                    val favoriteMealCollection = document.toObject(FavoriteMealCollection::class.java)
                    favoriteMeals.add(favoriteMealCollection!!.meal!!)
                }
                result.value = favoriteMeals
            }

        // Clean up the listener registration when the LiveData object is removed
        return result
    }
}