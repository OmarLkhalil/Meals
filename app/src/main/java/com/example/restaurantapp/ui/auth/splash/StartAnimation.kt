package com.example.restaurantapp.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.AppUser
import com.example.domain.util.signIn
import com.example.restaurantapp.DataUtils
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragementAnimationBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
            delay(2000)
            checkLoggedInUser()
            withContext(Dispatchers.Main){
                navController.navigate(R.id.action_startAnimation_to_splashFragment)
            }
        }
    }


    private fun checkLoggedInUser() {
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser == null) {
            startSplashActivity()
        } else {
            signIn(firebaseUser.uid, {
                val user = it.toObject(AppUser::class.java)
                DataUtils.user = user
                findNavController().navigate(R.id.action_startAnimation_to_navigation)
            }, {
                Toast.makeText(requireContext(), "can't login", Toast.LENGTH_LONG)
                    .show()
                startSplashActivity()
            })
        }
    }

    private fun startSplashActivity() {
        findNavController().navigate(R.id.action_startAnimation_to_splashFragment)
    }
}