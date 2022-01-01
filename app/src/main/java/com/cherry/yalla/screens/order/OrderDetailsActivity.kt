package com.cherry.yalla.screens.order

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.application.base.BaseActivity
import com.cherry.yalla.R
import com.cherry.yalla.databinding.ActivityJobDetailBinding
import com.cherry.yalla.databinding.ActivityOrderDetailsBinding
import com.cherry.yalla.model.JobModel
import com.cherry.yalla.screens.job.AcceptedJobActivity
import com.cherry.yalla.screens.job.adapter.JobItemAdapter
import com.cherry.yalla.screens.order.adapter.JobStatusItemAdapter

class OrderDetailsActivity : BaseActivity(), BaseActivity.OnRetryButtonClick {
    private lateinit var binding: ActivityOrderDetailsBinding
    var jobList = arrayListOf<JobModel>()
    lateinit var jobItemAdapter: JobStatusItemAdapter

    override fun getContentView(): View {
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun getRetryListener(): OnRetryButtonClick {
        return this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupRecyclerView(false)

        binding.paymentMethods.setOnClickListener {
            showPaymentDialog()
        }

    }

    private fun setupRecyclerView(showNote:Boolean) {
        binding.recyclerItems.setHasFixedSize((false))
        jobItemAdapter =
            JobStatusItemAdapter(this,showNote, jobList, object : JobStatusItemAdapter.OnItemClicked {
                override fun onRecyclerItemClicked(data: JobModel) {

                }

            })
        binding.recyclerItems.adapter = jobItemAdapter
    }

    override fun onRetryClick() {

    }

    private fun showPaymentDialog() {
        val progress = Dialog(this)
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progress.setContentView(R.layout.dialog_payment)

        val tvCard = progress.findViewById<View>(R.id.tvCard) as TextView

        tvCard.setOnClickListener(View.OnClickListener {
            progress.dismiss()
            binding.paymentMethods.text="Card on delivery (POS)"
            binding.paymentMethods.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_credit_card__1_, 0, 0, 0)
            setupRecyclerView(true)
        })
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(progress.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        progress.window!!.attributes = lp
        progress.setCancelable(true)
        progress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress.show()
    }
}