package com.cherry.yalla

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

class MainActivity : FragmentActivity() {

    lateinit var laySettings: LinearLayout
    lateinit var layProfile: LinearLayout
    lateinit var layJobs: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        laySettings = findViewById(R.id.laySettings)
        layProfile = findViewById(R.id.layProfile)
        layJobs = findViewById(R.id.layJobs)

        laySettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        layProfile.setOnClickListener {
            findNavController(this, R.id.container).navigate(R.id.profileFragment)
        }

        layProfile.setOnClickListener {
            findNavController(this, R.id.container).navigate(R.id.profileFragment)
        }

        layJobs.setOnClickListener {
//            findNavController(this, R.id.container).navigate(R.id.jobListFragment)
            findNavController(this, R.id.container).navigate(R.id.itemsToReturnFragment)
        }
    }

    private fun showNoteDialogue() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogue_note)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnSubmit: Button = dialog.findViewById(R.id.btnSubmit)
        val imgClose: ImageView = dialog.findViewById(R.id.imgClose)
        btnSubmit.setOnClickListener {
            dialog.dismiss()
        }
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showQidDialogue() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogue_qid)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val imgClose: ImageView = dialog.findViewById(R.id.imgClose)
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}