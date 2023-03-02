package com.example.restaurantapp.ui.auth.login

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.domain.util.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.entity.AppUser
import com.example.domain.usecase.SignInUseCase
import com.example.domain.util.hide
import com.example.domain.util.show
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentLoginBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    lateinit var navController: NavController
    lateinit var binding: FragmentLoginBinding

    val email = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value?.trim())
        }
    }

    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()

    fun login() {
        if (validate()) {
            checkAccountInRepo()
        }
    }

    private fun showProgress() {
        binding.progressBar.show()
    }

    private fun hideProgress() {
        binding.progressBar.hide()
    }

    lateinit var context: Context

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

    private fun checkAccountInRepo() {
        showProgress()
        signInUseCase.execute(
            SignInUseCase.Params(email.get()!!, password.get()!!),
            { docSnapshot ->
                hideProgress()
                val user = docSnapshot.toObject(AppUser::class.java)
                if (user == null) {
                    messageLiveData.value = "Valid Email or Password"
                    showError(messageLiveData.value.toString())
                } else {
                    DataUtils.user = user
                    navController.navigate(R.id.action_loginFragment_to_mainFragment2)
                }
            },
           { error ->
                hideProgress()
                showError(error.localizedMessage ?: "Unknown error")
            }
        )
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
