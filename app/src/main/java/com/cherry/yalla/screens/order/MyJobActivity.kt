package com.cherry.yalla.screens.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.databinding.ActivityLogBinding
import com.cherry.yalla.databinding.ActivityMyJobBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.AcceptedJobActivity
import com.cherry.yalla.screens.job.JobActivity
import com.cherry.yalla.screens.job.JobDetailActivity
import com.cherry.yalla.screens.job.adapter.JobAdapter
import com.cherry.yalla.screens.order.adapter.ActivityAdapter
import com.cherry.yalla.screens.order.adapter.JobListAdapter

class MyJobActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityMyJobBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: JobListAdapter

    override fun getContentView(): View {
        binding = ActivityMyJobBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.header.tvHeading.text="My Job"

        setupRecyclerView()
        binding.header.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= JobListAdapter(this,jobList, object : JobListAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
                startActivity(Intent(this@MyJobActivity, AcceptedJobActivity::class.java))
            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }
}