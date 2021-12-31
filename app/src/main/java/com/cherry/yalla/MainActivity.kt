package com.cherry.yalla

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {

    lateinit var laySettings: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        laySettings = findViewById(R.id.laySettings)

        laySettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}