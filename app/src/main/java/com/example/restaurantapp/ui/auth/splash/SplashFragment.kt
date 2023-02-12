package com.example.restaurantapp.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.domain.entity.AppUser
import com.example.domain.util.signIn
import com.example.restaurantapp.DataUtils
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashFragment : Fragment() {

    private var layouts: IntArray = intArrayOf(R.layout.view_red, R.layout.view_blue, R.layout.view_green)
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding : FragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        viewPager = binding.viewpager
        val adapter = CustomPagerAdapter(layouts, requireContext())
        viewPager.adapter = adapter

        binding.getStart.setOnClickListener{
            findNavController().navigate(R.id.action_Splash_to_RegisterFragment)
        }
        checkLoggedInUser()

        return binding.root
    }

    private fun checkLoggedInUser(){
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser == null){
            startLoginActivity()
        } else {
            signIn(firebaseUser.uid , {
                val user = it.toObject(AppUser::class.java)
                DataUtils.user = user
            }, {
                Toast.makeText(requireContext() , "can't login" , Toast.LENGTH_LONG)
                    .show()
                startLoginActivity()
            })
        }
    }
    private fun startLoginActivity() {
        findNavController().navigate(R.id.action_Splash_to_RegisterFragment)
    }
}