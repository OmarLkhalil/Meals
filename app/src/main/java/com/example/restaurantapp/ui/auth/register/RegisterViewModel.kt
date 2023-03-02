package com.example.restaurantapp.ui.auth.register

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.data.base.BaseViewModel
import com.example.domain.entity.AppUser
import com.example.domain.usecase.SignUpUseCase
import com.example.domain.util.hide
import com.example.domain.util.show
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(val signUpUseCase: SignUpUseCase) : BaseViewModel() {

    lateinit var navController: NavController
    lateinit var binding: FragmentRegisterBinding
    lateinit var googleSignInClient: GoogleSignInClient


    val name = ObservableField<String>()
    val nameError = ObservableField<String>()
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()


    // Sign in with Google
    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        (context as Activity).startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }

    fun createAccount() {
        if (validate()) {
            addAccountToFireBase()
        }
    }

    lateinit var context : Context

    fun showError(errorMessage: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(errorMessage)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun addAccountToFireBase() {
        showProgress()
        val user = AppUser(
            id = null,
            name = name.get(),
            email = email.get(),
            favoriteMealId = null
        )
        signUpUseCase.execute(user,
            onSuccess = {
                hideProgress()
                navController.navigate(R.id.action_registerFragment_to_mainFragment)
            },
            onFailure = { error ->
                hideProgress()
                showError(error.localizedMessage ?: "Unknown error")
            }
        )
    }

    fun navigateToLoginFragment(view: View) {
        findNavController(view).navigate(R.id.actionRegister_toLogin)
    }

    private fun validateField(
        field: ObservableField<String>,
        errorField: ObservableField<String>,
        errorMessage: String
    ): Boolean {
        return if (field.get().isNullOrBlank()) {
            errorField.set(errorMessage)
            false
        } else {
            errorField.set(null)
            true
        }
    }

    private fun showProgress() {
        binding.progressBar.show()
    }

    private fun hideProgress() {
        binding.progressBar.hide()
    }


    fun continueAsGuest(){
        navController.navigate(R.id.action_registerFragment_to_mainFragment)
    }

    private fun validate(): Boolean {
        var valid = true
        valid = validateField(name, nameError, "Enter First Name") && valid
        valid = validateField(email, emailError, "Enter Email") && valid
        valid = validateField(password, passwordError, "Enter Password") && valid
        return valid
    }


}