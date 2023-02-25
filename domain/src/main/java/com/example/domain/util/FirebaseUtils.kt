package com.example.domain.util

import com.example.domain.entity.AppUser
import com.example.domain.entity.FavoriteMeal
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


fun getCollection(collectionName: String):CollectionReference{
    val db = Firebase.firestore
    return db.collection(collectionName)
}

fun addUserToFireStore(user: AppUser,
                       onSuccessListener: OnSuccessListener<Void>,
                       onFailureListener: OnFailureListener) {
    val userCollection = getCollection(AppUser.COLLECTION_NAME)
    val userDoc = userCollection.document(user.id!!)
    userDoc.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun addFavoriteMeal(favoriteMeal: FavoriteMeal, userId: String,
                    onSuccessListener: OnSuccessListener<Void>,
                    onFailureListener: OnFailureListener) {
    val userCollection = getCollection("users")
    val favoriteMealsCollection = userCollection.document(userId)
        .collection("favoriteMeals")
    val favoriteMealDoc = favoriteMealsCollection.document(favoriteMeal.idMeal!!)
    favoriteMealDoc.set(favoriteMeal)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun signIn(uid:String,
           onSuccessListener: OnSuccessListener<DocumentSnapshot>,
           onFailureListener: OnFailureListener){
    val collectionRef = getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}
