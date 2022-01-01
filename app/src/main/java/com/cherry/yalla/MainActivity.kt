package com.cherry.yalla

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

class MainActivity : FragmentActivity() {

    lateinit var laySettings: LinearLayout
    lateinit var layProfile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        laySettings = findViewById(R.id.laySettings)
        layProfile = findViewById(R.id.layProfile)

        laySettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        layProfile.setOnClickListener {
            findNavController(this,R.id.container).navigate(R.id.profileFragment)
        }
    }
}