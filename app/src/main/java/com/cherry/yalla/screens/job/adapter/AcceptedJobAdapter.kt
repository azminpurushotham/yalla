package com.cherry.yalla.screens.job.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.cherry.yalla.R
import com.cherry.yalla.model.JobModel
import java.text.SimpleDateFormat
import java.util.*

class AcceptedJobAdapter (
    private val mContext: Context, private val mApps: ArrayList<JobModel>,
    var listener: OnItemClicked
) : RecyclerView.Adapter<AcceptedJobAdapter.ViewHolder>() {

    lateinit var onItemClicked: OnItemClicked

    interface OnItemClicked {
        fun onRecyclerItemClicked(data: JobModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        onItemClicked = listener
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_job_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position==0){
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.green)
            )
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvPayMode.background),
                ContextCompat.getColor(mContext, R.color.green)
            )
            holder.container.background= ContextCompat.getDrawable(mContext, R.drawable.shape_rounded_rect_green)
        }else if(position==1){
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.red)
            )
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvPayMode.background),
                ContextCompat.getColor(mContext, R.color.red)
            )
            holder.container.background= ContextCompat.getDrawable(mContext, R.drawable.shape_rounded_rect_red)
        }else if(position==2){
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.yellow)
            )
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvPayMode.background),
                ContextCompat.getColor(mContext, R.color.yellow)
            )
            holder.container.background= ContextCompat.getDrawable(mContext, R.drawable.shape_rounded_rect_yellow)
        }

        holder.tvShipping.setOnClickListener {
            if(holder.tvAddress.visibility==View.VISIBLE){
                holder.tvAddress.visibility=View.GONE
            }else{
                holder.tvAddress.visibility=View.VISIBLE
            }
        }

        holder.itemView.setOnClickListener {
            onItemClicked.onRecyclerItemClicked(JobModel())
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvShipping: TextView
        var tvAddress: TextView
        var tvNumber: TextView
        var tvPayMode: TextView
        var container: LinearLayout

        init {
            tvShipping = itemView.findViewById<View>(R.id.tvShipping) as TextView
            tvAddress = itemView.findViewById<View>(R.id.tvAddress) as TextView
            tvNumber = itemView.findViewById<View>(R.id.tvNumber) as TextView
            tvPayMode = itemView.findViewById<View>(R.id.tvPayMode) as TextView
            container = itemView.findViewById(R.id.container) as LinearLayout
        }
    }
}