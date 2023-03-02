package com.example.domain.repo

import com.example.domain.entity.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot

interface AuthenticationRepo {

    fun signIn(uid: String, onSuccessListener: OnSuccessListener<DocumentSnapshot>, onFailureListener: OnFailureListener)

    fun signUp(user: AppUser, onSuccessListener: OnSuccessListener<Void>, onFailureListener: OnFailureListener)

    fun getUser(): AppUser?

}