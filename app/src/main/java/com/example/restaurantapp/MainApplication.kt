package com.example.restaurantapp

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp


// The @HiltAndroidApp annotation indicates that the class is using the Hilt library for dependency injection in the app.
@HiltAndroidApp
class MainApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}