package com.example.restaurantapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivitySplashBinding
import com.example.restaurantapp.ui.register.RegisterActivity


class SplashActivity : AppCompatActivity() {

    private var layouts: IntArray = intArrayOf(R.layout.view_red, R.layout.view_blue, R.layout.view_green)
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        viewPager = findViewById(R.id.viewpager)
        val adapter = CustomPagerAdapter(layouts,this)
        handler.postDelayed(swipeTask, DELAY)
        viewPager.adapter = adapter

        binding.getStart.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private var currentPage = 0
    private var handler = Handler()
    private val DELAY: Long = 3000

    private val swipeTask = Runnable {
        if (currentPage == layouts.size - 1) {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        } else {
            viewPager.currentItem = currentPage++
            handler.postDelayed({

            }, DELAY)
        }
    }
}