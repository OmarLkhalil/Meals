package com.example.restaurantapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.restaurantapp.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Hide the toolbar and nav drawer when the app starts
        val toolbar = findViewById<MaterialToolbar>(R.id.appBarToolbar)
        toolbar.visibility = View.GONE

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.visibility = View.GONE

        // Set up the nav drawer
        setUpNavDrawer()
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.home_nav_host).navigateUp(appBarConfiguration)


    private fun setUpNavDrawer() {

        drawerLayout = findViewById(R.id.drawer_layout)

        // Find the nav host fragment and set up the NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment
        navController = navHostFrag.navController

        // Find the toolbar and set it as the ActionBar
        val toolbar = findViewById<MaterialToolbar>(R.id.appBarToolbar)
        setSupportActionBar(toolbar)

        // Set up the AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.favoriteFragment
            ),
            drawerLayout
        )

        // Set up the ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        // Set up the NavView
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setupWithNavController(navController)

        // Add a destination changed listener to show or hide the toolbar and nav drawer
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> {
                    // Show the toolbar and nav drawer
                    toolbar.visibility = View.VISIBLE
                    navView.visibility = View.VISIBLE

                    // Set the AppBarConfiguration for the home screen
                    appBarConfiguration = AppBarConfiguration(
                        setOf(
                            R.id.mainFragment,
                            R.id.favoriteFragment
                        ),
                        drawerLayout
                    )

                    // Enable the drawer toggle
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                else -> {
                    // Hide the toolbar and nav drawer
                    toolbar.visibility = View.GONE
                    navView.visibility = View.GONE

                    // Set the AppBarConfiguration for other screens
                    appBarConfiguration = AppBarConfiguration(
                        setOf(
                            destination.id
                        ),
                        drawerLayout
                    )

                    // Disable the drawer toggle
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }

        // Set up the ActionBarDrawerToggle
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }



    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val navController = findNavController(R.id.home_nav_host)
        if (navController.currentDestination?.id == R.id.mainFragment) {
            // If the current fragment is the home screen, close the app
            super.onBackPressed()
        } else {
            // Otherwise, navigate back to the last fragment
            navController.popBackStack()
        }
    }

}
