package com.example.restaurantapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class AuthMainActivity : AppCompatActivity() {

    lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_start) as NavHostFragment
        navController   = navHostFrag.navController
    }
}