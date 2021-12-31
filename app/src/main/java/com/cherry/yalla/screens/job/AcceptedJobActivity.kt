package com.cherry.yalla.screens.job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.R
import com.cherry.yalla.databinding.ActivityAcceptedJobBinding
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.AcceptedJobAdapter
import com.cherry.yalla.screens.job.adapter.JobAdapter

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
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= AcceptedJobAdapter(this,jobList, object : AcceptedJobAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
                startActivity(Intent(this@AcceptedJobActivity,JobDetailActivity::class.java))
            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }
}