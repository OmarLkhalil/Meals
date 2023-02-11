package com.example.restaurantapp.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentLoginBinding

class LoginFragment :Fragment() {

    private lateinit var binding   : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding

        binding.loginRegisterTv.setOnClickListener{
            viewModel.navigateToRegister(it)
        }

        binding.buttonLogin.setOnClickListener{
            viewModel.login()
        }

        return binding.root
    }
}
