package com.cherry.yalla.screens.job

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.databinding.ActivityAcceptedJobBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.AcceptedJobAdapter
import com.cherry.yalla.screens.order.OrderDetailsActivity


class AcceptedJobActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityAcceptedJobBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: AcceptedJobAdapter

    override fun getContentView(): View {
        binding = ActivityAcceptedJobBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupRecyclerView()
        binding.header.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.topContainer.setOnClickListener {
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter = AcceptedJobAdapter(this, jobList, object : AcceptedJobAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel, pos: Int) {
//                startActivity(Intent(this@AcceptedJobActivity,JobDetailActivity::class.java))
            startActivity(Intent(this@AcceptedJobActivity, OrderDetailsActivity::class.java))
            }

        })
        binding.recyclerJob.adapter = jobAdapter
    }

    override fun onRetryClick() {

    }


}