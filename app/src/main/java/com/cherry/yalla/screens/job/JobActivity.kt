package com.cherry.yalla.screens.job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.R
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.JobAdapter

class JobActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityJobBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: JobAdapter

    override fun getContentView(): View {
        binding = ActivityJobBinding.inflate(layoutInflater)
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
        jobAdapter= JobAdapter(this,jobList, object : JobAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
                startActivity(Intent(this@JobActivity,JobDetailActivity::class.java))
            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }
}