package com.example.restaurantapp.ui.register

import androidx.databinding.ObservableField
import com.example.restaurantapp.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.util.addUserToFireStore
import com.example.domain.entity.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterViewModel: BaseViewModel<Navigator>() {

    val name = ObservableField<String>()
    val nameError = ObservableField<String>()
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    private val auth = Firebase.auth


    fun createAccount() {
        if (validate()) {
            addAccountToFireBase()
        }
    }

    private fun addAccountToFireBase() {
        showLoading.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (!task.isSuccessful) {
                    //show error message
                    messageLiveData.value = task.exception!!.localizedMessage
                } else {
                    //show success message
                    //messageLiveData.value = "success registration"
                    //navigator?.openHomeScreen()
                    createFirestoreUser(task.result.user?.uid)
                }
            }
    }

    private fun createFirestoreUser(uid: String?) {
        showLoading.value = true
        val user = AppUser(
            id = uid,
            name = name.get(),
            email = email.get()
        )
        addUserToFireStore(user, {
            showLoading.value = false
            DataUtils.user = user
            navigator?.openHomeScreen()
        }, {
            showLoading.value = false
            messageLiveData.value = it.localizedMessage
        })
    }

    fun openLogin(){
        navigator?.openLoginScreen()
    }

    private fun validate(): Boolean {
        var valid = true
        if (name.get().isNullOrBlank()) {
            nameError.set("Enter First Name")
            valid = false
        } else {
            nameError.set(null)
        }
        if (email.get().isNullOrBlank()) {
            emailError.set("Enter Email")
            valid = false
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("Enter Password")
            valid = false
        } else {
            passwordError.set(null)
        }
        return valid
    }
}