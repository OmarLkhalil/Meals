package com.example.restaurantapp.ui.auth.register

import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.restaurantapp.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.util.addUserToFireStore
import com.example.domain.entity.AppUser
import com.example.domain.util.hide
import com.example.domain.util.show
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterViewModel : BaseViewModel() {

    lateinit var navController: NavController

    lateinit var binding: FragmentRegisterBinding

    val name = ObservableField<String>()
    val nameError = ObservableField<String>()
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    private val auth = Firebase.auth

    lateinit var googleSignInClient: GoogleSignInClient

    fun createAccount() {
        if (validate()) {
            addAccountToFireBase()
        }
    }

    private fun addAccountToFireBase() {
        showProgress()
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                hideProgress()
                if (!task.isSuccessful) {
                    //show error message
                    task.exception!!.localizedMessage?.let { showError(it) }
                } else {
                    //show success message
                    //messageLiveData.value = "success registration"
                    navController.navigate(R.id.action_registerFragment_to_mainFragment)
                    createFireStoreUser(task.result.user?.uid)
                }
            }
    }

    private fun createFireStoreUser(uid: String?) {
        showProgress()
        val user = AppUser(
            id = uid,
            name = name.get(),
            email = email.get(),
            favoriteMealId = null
        )
        addUserToFireStore(user, {
            hideProgress()
            DataUtils.user = user
        }, {
            hideProgress()
            it.localizedMessage?.let { it1 -> showError(it1) }
        })
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

    private fun showError(errorMessage: String) {
        messageLiveData.value = errorMessage

    }

    fun firebaseAuthWithGoogle(idToken: String) {
        showProgress()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                hideProgress()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // Save user data to Firestore
                    createFireStoreUser(user?.uid)
                } else {
                    // If sign in fails, display a message to the user.
                    task.exception?.localizedMessage?.let { showError(it) }
                }
            }
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