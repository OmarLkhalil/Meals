package com.example.domain.util


import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


fun getCollection(collectionName: String):CollectionReference{
    val db = Firebase.firestore
    return db.collection(collectionName)
}
