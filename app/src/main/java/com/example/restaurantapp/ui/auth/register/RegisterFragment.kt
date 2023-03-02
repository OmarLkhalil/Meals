package com.example.restaurantapp.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(){

    private lateinit var binding   : FragmentRegisterBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        initVariables()
        setUpListeners()
        return binding.root
    }


    private fun setUpListeners(){

        binding.SignInTxt.setOnClickListener{
            viewModel.navigateToLoginFragment(it)
        }

        binding.SignUpButton.setOnClickListener{
            viewModel.createAccount()
        }

        binding.continueAsGuest.setOnClickListener{
            viewModel.continueAsGuest()
        }

    }

    private fun initVariables(){

        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding
        viewModel.context = requireContext()

    }

}