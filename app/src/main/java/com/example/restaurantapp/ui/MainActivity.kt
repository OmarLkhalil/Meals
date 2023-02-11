package com.example.restaurantapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.restaurantapp.R
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var navView : NavigationView
    lateinit var toolBar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // setup the toolbar

        toolBar  = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)

        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.mainFragment -> {

                }
                R.id.favoriteFragment -> {

                }
                R.id.settings -> {

                }
                R.id.sign_out -> {

                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}