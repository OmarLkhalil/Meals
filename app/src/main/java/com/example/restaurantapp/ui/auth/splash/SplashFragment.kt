package com.example.restaurantapp.ui.auth.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var layouts: IntArray =
        intArrayOf(R.layout.view_red, R.layout.view_blue, R.layout.view_green)
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSplashBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        viewPager = binding.viewpager
        val adapter = CustomPagerAdapter(layouts, requireContext())
        viewPager.adapter = adapter

        binding.getStart.setOnClickListener {
            findNavController().navigate(R.id.action_Splash_to_RegisterFragment)
        }

        return binding.root
    }
}

//    override fun onResume() {
//        super.onResume()
//
//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageSelected(position: Int) {
//                if (layouts[position] == R.layout.fragement_animation) {
//                    getStart.visibility = View.GONE
//
//                    // start animation and navigate to next fragment after 50 seconds
//                    Handler().postDelayed({
//                        // navigate to next fragment
//                        viewPager.currentItem = position + 1
//                        viewPager.adapter?.notifyDataSetChanged()
//                    }, 50000) // 50 seconds delay
//                } else {
//                    getStart.visibility = View.VISIBLE
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {}
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {}
//        })
//    }
//
