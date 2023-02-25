package com.example.restaurantapp.ui.auth.login

import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.restaurantapp.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.util.signIn
import com.example.domain.entity.AppUser
import com.example.domain.util.hide
import com.example.domain.util.show
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: BaseViewModel() {

    lateinit var navController: NavController
    lateinit var binding: FragmentLoginBinding

    val email = object : ObservableField<String>(){
        override fun set(value: String?) {
                super.set(value?.trim())
            }
    }

    val emailError = ObservableField<String>()
    val password   = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val auth = Firebase.auth


    fun login() {
        if (validate()) {
            checkAccountInFireBase()
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


    private fun checkAccountInFireBase() {
        showProgress()
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                hideProgress()
                if (!task.isSuccessful) {
                    //show error message
                    showError(task.exception.toString())
                } else {
                    //show success message
                    //messageLiveData.value = "success Login"
                    navController.navigate(R.id.action_loginFragment_to_mainFragment2)
                    checkUserFromFireStore(task.result.user?.uid)
                }
            }
    }

    private fun checkUserFromFireStore(uid: String?) {
        showProgress()
        signIn(uid!!, OnSuccessListener { docSnapshot ->
            hideProgress()
            val user = docSnapshot.toObject(AppUser::class.java)
            if (user == null) {
                messageLiveData.value = "Valid Email or Password"
                showError(messageLiveData.value.toString())
                return@OnSuccessListener
            } else {
                DataUtils.user = user
            }
        }, onFailureListener = {
            hideProgress()
            it.localizedMessage?.let { it1 -> showError(it1) }
        })
    }

    fun navigateToRegister(view: View) {
        findNavController(view).navigate(R.id.actionLogin_toRegister)
    }
    fun navigateToForgetPassword(view: View) {
        findNavController(view).navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
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

    private fun validate(): Boolean {
        var valid = true
        valid = validateField(email, emailError, "Enter Email") && valid
        valid = validateField(password, passwordError, "Enter Password") && valid
        return valid
    }
}