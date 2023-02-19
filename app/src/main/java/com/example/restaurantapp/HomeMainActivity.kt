package com.example.restaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class HomeMainActivity : AppCompatActivity() {

    lateinit var  drawerLayout   : DrawerLayout
    lateinit var  navController  : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.appBarToolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.menu)

        setUpNavDrawer()
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host).navigateUp(appBarConfiguration)


    private fun setUpNavDrawer(){
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.favoriteFragment,
            ),
            findViewById<DrawerLayout>(R.id.drawer_layout)
        )
        with(findNavController(R.id.nav_host)){
            findViewById<NavigationView>(R.id.nav_view).setupWithNavController(this)
            setupActionBarWithNavController(this, appBarConfiguration)
            enableDrawer()
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun enableDrawer() {
        supportActionBar?.show()
        drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)
    }
}