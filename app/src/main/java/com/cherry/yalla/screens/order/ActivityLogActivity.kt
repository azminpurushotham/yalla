package com.cherry.yalla.screens.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.databinding.ActivityLogBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.JobDetailActivity
import com.cherry.yalla.screens.job.adapter.JobAdapter
import com.cherry.yalla.screens.order.adapter.ActivityAdapter

class ActivityLogActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityLogBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: ActivityAdapter

    override fun getContentView(): View {
        binding = ActivityLogBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= ActivityAdapter(this,jobList, object : ActivityAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {

            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }
}