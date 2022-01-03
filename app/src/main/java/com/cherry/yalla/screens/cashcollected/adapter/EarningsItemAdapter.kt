package com.cherry.yalla.screens.cashcollected.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cherry.yalla.R
import com.cherry.yalla.model.JobModel
import java.text.SimpleDateFormat
import java.util.*

class EarningsItemAdapter (
    private val mContext: Context, private val mApps: ArrayList<JobModel>,
    var listener: OnItemClicked
) : RecyclerView.Adapter<EarningsItemAdapter.ViewHolder>() {

    lateinit var onItemClicked: OnItemClicked

    interface OnItemClicked {
        fun onRecyclerItemClicked(data: JobModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        onItemClicked = listener
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_earnings, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener { listener.onRecyclerItemClicked(JobModel()) }

    }

    override fun getItemCount(): Int {
        return 2
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var tvShipping: TextView
//        var tvAddress: TextView

        init {
//            tvShipping = itemView.findViewById<View>(R.id.tvShipping) as TextView
//            tvAddress = itemView.findViewById<View>(R.id.tvAddress) as TextView
        }
    }
}