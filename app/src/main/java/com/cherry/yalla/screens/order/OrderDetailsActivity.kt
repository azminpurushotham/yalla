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
import android.widget.Button
import android.widget.ImageView
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
        binding.header.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.header.tvHeading.text="Order #13452455"
        binding.btnUpdate.setOnClickListener {
            showOrderDialog(0)
        }

        binding.btnNotification.setOnClickListener {
            startActivity(Intent(this@OrderDetailsActivity,ActivityLogActivity::class.java))
        }

        binding.paymentMethods.setOnClickListener {
            showPaymentDialog()
        }

        binding.btnCancel.setOnClickListener {
            showOrderDialog(2)
        }

    }

    private fun setupRecyclerView(showNote:Boolean) {
        binding.recyclerItems.setHasFixedSize((false))
        jobItemAdapter =
            JobStatusItemAdapter(this,showNote, jobList, object : JobStatusItemAdapter.OnItemClicked {
                override fun onRecyclerItemClicked(data: JobModel,pos:Int) {
                    if (pos==1) {
                        showOrderDialog(pos)
                    }
                }

                override fun onQtyItemClicked(data: JobModel, pos: Int) {
                    showNoteDialogue()
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

    private fun showOrderDialog(pos:Int) {
        val progress = Dialog(this)
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(pos==0) {
            progress.setContentView(R.layout.dialog_order_done)
        }else if(pos==2){
            progress.setContentView(R.layout.dialog_order_cancel)
        }else if(pos==1){
            progress.setContentView(R.layout.dialog_order_edited)
        }

        val btOk = progress.findViewById<View>(R.id.btBack) as AppCompatButton

        btOk.setOnClickListener(View.OnClickListener {
            progress.dismiss()

        })
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(progress.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        progress.window!!.attributes=lp
        progress.setCancelable(true)
        progress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress.show()
    }

    private fun showNoteDialogue() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogue_note)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnSubmit: Button = dialog.findViewById(R.id.btnSubmit)
        val imgClose: ImageView = dialog.findViewById(R.id.imgClose)
        btnSubmit.setOnClickListener {
            dialog.dismiss()
        }
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}