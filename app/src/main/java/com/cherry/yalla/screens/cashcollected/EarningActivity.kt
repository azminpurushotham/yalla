package com.cherry.yalla.screens.cashcollected

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.R
import com.cherry.yalla.databinding.ActivityAcceptedJobBinding
import com.cherry.yalla.databinding.ActivityEarningBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.cashcollected.adapter.EarningsItemAdapter
import com.cherry.yalla.screens.job.adapter.AcceptedJobAdapter
import com.cherry.yalla.screens.order.OrderDetailsActivity

class EarningActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityEarningBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: EarningsItemAdapter

    override fun getContentView(): View {
        binding = ActivityEarningBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): BaseActivity.OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupRecyclerView()
        binding.header.tvHeading.text="Earnings"
        binding.header.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= EarningsItemAdapter(this,jobList, object : EarningsItemAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
//                startActivity(Intent(this@AcceptedJobActivity,JobDetailActivity::class.java))

            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }



}