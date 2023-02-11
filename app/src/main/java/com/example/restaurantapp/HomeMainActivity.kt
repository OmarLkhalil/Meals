package com.example.restaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class HomeMainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var navView : NavigationView
    lateinit var toolBar : Toolbar
    lateinit var  navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // setup the toolbar
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController           = navHostFrag.navController

        toolBar  = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = ""

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        toolBar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isOpen) {
            drawerLayout.close()
        } else {
            super.onBackPressed()
        }
    }
}