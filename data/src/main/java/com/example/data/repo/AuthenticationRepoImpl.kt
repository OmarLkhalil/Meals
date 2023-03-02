package com.example.data.repo

import com.example.domain.entity.AppUser
import com.example.domain.repo.AuthenticationRepo
import com.example.domain.util.getCollection
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class AuthenticationRepoImpl : AuthenticationRepo {

    override fun signIn(
        uid: String,
        onSuccessListener: OnSuccessListener<DocumentSnapshot>,
        onFailureListener: OnFailureListener
    ) {
     val collectionRef = getCollection(AppUser.COLLECTION_NAME)
     collectionRef.document(uid)
      .get()
      .addOnSuccessListener(onSuccessListener)
      .addOnFailureListener(onFailureListener)
    }



    override fun signUp(
        user: AppUser,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        val userCollection = getCollection(AppUser.COLLECTION_NAME)
        val userId = user.id ?: UUID.randomUUID().toString()
        val userDoc = userCollection.document(userId)
        userDoc.set(user.copy(id = userId))
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override fun getUser(): AppUser? {

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return if (firebaseUser != null) {
            AppUser(firebaseUser.uid, firebaseUser.email)
        } else {
            null
        }
    }
}