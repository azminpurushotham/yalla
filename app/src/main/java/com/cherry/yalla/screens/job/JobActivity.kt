package com.cherry.yalla.screens.job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cherry.yalla.R
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.JobAdapter

class JobActivity : AppCompatActivity() {

    var jobList= arrayListOf<JobModel>()
    lateinit var jobAdapter:JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

    }
}