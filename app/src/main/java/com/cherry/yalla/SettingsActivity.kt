package com.cherry.yalla

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class SettingsActivity : ComponentActivity() {
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

    }
}