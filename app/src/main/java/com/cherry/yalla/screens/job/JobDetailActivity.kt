package com.cherry.yalla.screens.job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.databinding.ActivityJobDetailBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.JobAdapter
import com.cherry.yalla.screens.job.adapter.JobItemAdapter
import com.cherry.yalla.screens.order.OrderDetailsActivity

class JobDetailActivity : BaseActivity(), BaseActivity.OnRetryButtonClick  {
    private lateinit var binding: ActivityJobDetailBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobItemAdapter: JobItemAdapter

    override fun getContentView(): View {
        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupRecyclerView()
        binding.header.tvHeading.text="Order #13452455"
//        binding.tvPayMode.setOnClickListener {
//
//        }
    }

    private fun setupRecyclerView() {
        binding.recyclerItems.setHasFixedSize((false))
        jobItemAdapter= JobItemAdapter(this,jobList, object : JobItemAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
                startActivity(Intent(this@JobDetailActivity,OrderDetailsActivity::class.java))
            }

        })
        binding.recyclerItems.adapter=jobItemAdapter
    }

    override fun onRetryClick() {

    }
}