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

class ActivityAdapter(
    private val mContext: Context,
    private val mApps: ArrayList<JobModel>,
    var listener: OnItemClicked
) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    lateinit var onItemClicked: OnItemClicked

    interface OnItemClicked {
        fun onRecyclerItemClicked(data: JobModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        onItemClicked = listener
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_activity, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.calendarLayout.visibility = View.GONE
            holder.imageLayout.visibility = View.GONE
            holder.time.visibility=View.INVISIBLE
        } else if (position == 1) {
            holder.calendarLayout.visibility = View.GONE
            holder.imageLayout.visibility = View.VISIBLE
            holder.time.visibility=View.INVISIBLE
        } else if (position == 2) {
            holder.calendarLayout.visibility = View.VISIBLE
            holder.imageLayout.visibility = View.GONE
            holder.time.visibility=View.VISIBLE
        }else{
            holder.calendarLayout.visibility = View.GONE
            holder.imageLayout.visibility = View.GONE
            holder.time.visibility=View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageLayout: LinearLayout
        var calendarLayout: LinearLayout
        var time:TextView

        init {
            imageLayout = itemView.findViewById<View>(R.id.imageLayout) as LinearLayout
            calendarLayout = itemView.findViewById<View>(R.id.calendarLayout) as LinearLayout
            time = itemView.findViewById<View>(R.id.time) as TextView
        }
    }
}