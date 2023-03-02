package com.example.restaurantapp.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragementAnimationBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StartAnimation : Fragment(), CoroutineScope {

    private lateinit var navController: NavController
    private lateinit var binding: FragementAnimationBinding

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragement_animation, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            // Check if user is already signed in
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                startHomeFragment()
            } else {
                delay(2000)
                withContext(Dispatchers.Main) {
                    startSplashFragment()
                }
            }
        }
    }

    private fun startSplashFragment() {
        findNavController().navigate(R.id.action_startAnimation_to_splashFragment)
    }
    private fun startHomeFragment() {
        findNavController().navigate(R.id.action_startAnimation_to_navigation)
    }
}
