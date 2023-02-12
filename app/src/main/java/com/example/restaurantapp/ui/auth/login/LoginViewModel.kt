package com.example.restaurantapp.ui.auth.login

import android.view.View
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.restaurantapp.DataUtils
import com.example.data.base.BaseViewModel
import com.example.domain.util.signIn
import com.example.domain.entity.AppUser
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
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val auth = Firebase.auth


    fun login() {
        if (validate()) {
            checkAccountInFireBase()
        }
    }

    private fun checkAccountInFireBase() {
        showLoading.value = true
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (!task.isSuccessful) {
                    //show error message
                    messageLiveData.value = task.exception!!.localizedMessage
                } else {
                    //show success message
                    //messageLiveData.value = "success Login"
                    navController.navigate(R.id.action_login_to_home)
                    checkUserFromFireStore(task.result.user?.uid)
                }
            }
    }

    private fun checkUserFromFireStore(uid: String?) {
        showLoading.value = true
        signIn(uid!!, OnSuccessListener { docSnapshot ->
            showLoading.value = false
            val user = docSnapshot.toObject(AppUser::class.java)
            if (user == null) {
                messageLiveData.value = "Valid Email or Password"
                return@OnSuccessListener
            } else {
                DataUtils.user = user
            }
        }, onFailureListener = {
            showLoading.value = false
            messageLiveData.value = it.localizedMessage
        })
    }

    fun navigateToRegister(view: View) {
        findNavController(view).navigate(R.id.actionLogin_toRegister)
    }

    private fun validate(): Boolean {
        var valid = true
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