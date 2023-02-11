package com.example.restaurantapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentSplashBinding
import com.example.restaurantapp.ui.MainActivity


class SplashFragment : Fragment() {

    private var layouts: IntArray = intArrayOf(R.layout.view_red, R.layout.view_blue, R.layout.view_green)
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        viewPager = binding.viewpager
        val adapter = CustomPagerAdapter(layouts, requireContext())
        viewPager.adapter = adapter

        binding.getStart.setOnClickListener{
            findNavController().navigate(R.id.action_Splash_to_RegisterFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


}
