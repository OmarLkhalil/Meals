package com.example.domain.usecase

import com.example.domain.entity.AppUser
import com.example.domain.repo.AuthenticationRepo

class SignUpUseCase(private val repo: AuthenticationRepo) {

    fun execute(user: AppUser, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        repo.signUp(user, {
            onSuccess()
        }, {
            onFailure(it)
        })
    }

}