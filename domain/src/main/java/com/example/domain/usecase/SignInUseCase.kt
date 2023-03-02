package com.example.domain.usecase

import com.example.domain.repo.AuthenticationRepo
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot

class SignInUseCase(private val repo: AuthenticationRepo) {

    data class Params(val email: String, val password: String)

    fun execute(
        params: Params,
        onSuccessListener: OnSuccessListener<DocumentSnapshot>,
        onFailureListener: OnFailureListener
    ) {
        repo.signIn(params.email, onSuccessListener, onFailureListener)
    }
}