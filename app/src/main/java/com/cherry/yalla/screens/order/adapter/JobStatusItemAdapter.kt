package com.cherry.yalla.screens.order.adapter

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

class JobStatusItemAdapter(
    private val mContext: Context,
    private val showNote: Boolean,
    private val mApps: ArrayList<JobModel>,
    var listener: OnItemClicked
) : RecyclerView.Adapter<JobStatusItemAdapter.ViewHolder>() {

    lateinit var onItemClicked: OnItemClicked

    interface OnItemClicked {
        fun onRecyclerItemClicked(data: JobModel,pos:Int)
        fun onQtyItemClicked(data: JobModel,pos:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        onItemClicked = listener
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_job_items_status, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteLayout.visibility = View.GONE
        if (position == 0) {
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.light_green)
            )

            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tickContainer.background),
                ContextCompat.getColor(mContext, R.color.light_green)
            )

        } else if (showNote && position == 1) {
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.yellow)
            )

            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tickContainer.background),
                ContextCompat.getColor(mContext, R.color.yellow)
            )

            holder.noteLayout.visibility = View.VISIBLE
        } else {
            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tvNumber.background),
                ContextCompat.getColor(mContext, R.color.grey)
            )

            DrawableCompat.setTint(
                DrawableCompat.wrap(holder.tickContainer.background),
                ContextCompat.getColor(mContext, R.color.grey)
            )
        }

        holder.tvQty.setOnClickListener { listener.onQtyItemClicked(JobModel(),position) }
        holder.itemView.setOnClickListener { listener.onRecyclerItemClicked(JobModel(),position) }
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvNumber: TextView
        var tickContainer: LinearLayout
        var noteLayout: LinearLayout
        var tvQty:TextView

        init {
            tvNumber = itemView.findViewById<View>(R.id.tvNumber) as TextView
            tickContainer = itemView.findViewById<View>(R.id.tickContainer) as LinearLayout
            noteLayout = itemView.findViewById<View>(R.id.noteLayout) as LinearLayout
            tvQty=itemView.findViewById(R.id.tvQty) as TextView
        }
    }
}