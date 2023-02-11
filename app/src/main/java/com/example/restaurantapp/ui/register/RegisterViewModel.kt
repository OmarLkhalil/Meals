package com.example.restaurantapp.ui.register

import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.restaurantapp.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.util.addUserToFireStore
import com.example.domain.entity.AppUser
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterViewModel: BaseViewModel() {

    lateinit var navController: NavController

    lateinit var binding: FragmentRegisterBinding

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
                    navController.navigate(R.id.actionRegister_toHome)
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


        }, {
            showLoading.value = false
            messageLiveData.value = it.localizedMessage
        })
    }

    fun navigateToLoginFragment(view: View) {
        findNavController(view).navigate(R.id.actionRegister_toLogin)
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