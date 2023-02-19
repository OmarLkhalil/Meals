package com.example.restaurantapp.ui.auth.forgetpassword

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
import com.example.restaurantapp.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment(){

    private lateinit var binding: FragmentForgetPasswordBinding
    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false)
        init()
        setListeners()
        return binding.root
    }

    private fun init(){
        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding
        viewModel.context = requireContext()
    }

    private fun setListeners(){
        binding.resetButton.setOnClickListener{
            viewModel.reset()
        }
    }


}