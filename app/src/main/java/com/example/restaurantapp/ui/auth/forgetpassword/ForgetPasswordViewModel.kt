package com.example.restaurantapp.ui.auth.forgetpassword

import android.app.AlertDialog
import android.content.Context
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import com.example.data.base.BaseViewModel
import com.example.domain.util.hide
import com.example.domain.util.show
import com.example.restaurantapp.databinding.FragmentForgetPasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ForgetPasswordViewModel : BaseViewModel(){

    lateinit var navController: NavController
    lateinit var binding: FragmentForgetPasswordBinding

    val email = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            value?.trim()
        }
    }

    lateinit var context: Context

    val emailError = ObservableField<String>()

    val auth = Firebase.auth

    private fun showProgress(){
        binding.progressBar.show()
    }
    private fun hideProgress(){
        binding.progressBar.hide()
    }

    fun reset(){
        if(validate()){
            sendResetPassword(email.get()!!)
        }
    }

    private fun showError(errorMessage: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(errorMessage)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun sendResetPassword(email: String) {
        showProgress()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                hideProgress()
                if (task.isSuccessful) {
                    messageLiveData.value = "Password reset email sent to $email"
                    showError(messageLiveData.value.toString())
                } else {
                    messageLiveData.value = task.exception!!.localizedMessage
                    showError(messageLiveData.value.toString())
                }
            }
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
        return valid
    }
}