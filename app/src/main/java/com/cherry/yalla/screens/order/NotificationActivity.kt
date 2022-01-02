package com.cherry.yalla.screens.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.application.base.BaseActivity
import com.cherry.yalla.databinding.ActivityMyJobBinding
import com.cherry.yalla.databinding.ActivityNotificationBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.JobActivity
import com.cherry.yalla.screens.order.adapter.JobListAdapter
import com.cherry.yalla.screens.order.adapter.NotificationAdapter

class NotificationActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {

    private lateinit var binding: ActivityNotificationBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobAdapter: NotificationAdapter

    override fun getContentView(): View {
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.header.tvHeading.text="Notifications"

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerJob.setHasFixedSize((false))
        jobAdapter= NotificationAdapter(this,jobList, object : NotificationAdapter.OnItemClicked {
            override fun onRecyclerItemClicked(data: JobModel) {
                startActivity(Intent(this@NotificationActivity, JobActivity::class.java))
            }

        })
        binding.recyclerJob.adapter=jobAdapter
    }

    override fun onRetryClick() {

    }
}