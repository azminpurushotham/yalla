package com.cherry.yalla.screens.job

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.application.base.BaseActivity
import com.cherry.yalla.R
import com.cherry.yalla.databinding.ActivityAcceptedJobBinding
import com.cherry.yalla.databinding.ActivityJobBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.adapter.AcceptedJobAdapter
import com.cherry.yalla.screens.job.adapter.JobAdapter
import android.view.Gravity

import android.view.WindowManager
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

        binding.topContainer.setOnClickListener {
            startActivity(Intent(this@AcceptedJobActivity,OrderDetailsActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= AcceptedJobAdapter(this,jobList, object : AcceptedJobAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel,pos:Int) {
//                startActivity(Intent(this@AcceptedJobActivity,JobDetailActivity::class.java))

            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }



}