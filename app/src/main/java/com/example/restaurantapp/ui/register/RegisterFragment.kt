package com.example.restaurantapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.example.restaurantapp.ui.MainActivity

class RegisterFragment : Fragment(){

    private lateinit var binding   : FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding

        binding.registerLoginTv.setOnClickListener{
            viewModel.navigateToLoginFragment(it)
        }

        binding.buttonCreateAccount.setOnClickListener{
            viewModel.createAccount()
        }

        return binding.root
    }

}

