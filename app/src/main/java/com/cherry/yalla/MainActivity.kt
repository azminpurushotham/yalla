package com.cherry.yalla

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation.findNavController
import com.cherry.yalla.screens.order.MyJobActivity
import com.cherry.yalla.screens.order.NotificationActivity

class MainActivity : FragmentActivity() {

    lateinit var laySettings: LinearLayout
    lateinit var layProfile: LinearLayout
    lateinit var layJobs: ConstraintLayout
    lateinit var layNotification: LinearLayout
    lateinit var layHome: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        laySettings = findViewById(R.id.laySettings)
        layProfile = findViewById(R.id.layProfile)
        layJobs = findViewById(R.id.layJobs)
        layNotification = findViewById(R.id.layNotification)
        layHome = findViewById(R.id.layHome)

        laySettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        layProfile.setOnClickListener {
            findNavController(this, R.id.container).navigate(R.id.profileFragment)
        }

        layProfile.setOnClickListener {
            findNavController(this, R.id.container).navigate(R.id.profileFragment)
        }

        layHome.setOnClickListener {
            findNavController(this, R.id.container).navigate(R.id.homeFragment)
        }

        layJobs.setOnClickListener {
//            findNavController(this, R.id.container).navigate(R.id.jobListFragment)
//            findNavController(this, R.id.container).navigate(R.id.itemsToReturnFragment)
            startActivity(Intent(this@MainActivity, MyJobActivity::class.java))
        }

        layNotification.setOnClickListener {
            startActivity(Intent(this@MainActivity, NotificationActivity::class.java))
        }
    }

}