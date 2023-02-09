package com.example.restaurantapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurantapp.R
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}