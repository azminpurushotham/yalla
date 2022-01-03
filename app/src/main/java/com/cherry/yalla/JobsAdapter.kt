package com.cherry.yalla

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobsAdapter(private val list: List<String>, private val onClicked: () -> Unit) :
    RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jobs, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.txtMonth.text = list[position]
        holder.itemView.setOnClickListener {
            Log.v("JobsAdapter"," position $position")
            onClicked()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val txtMonth: TextView = itemView.findViewById(R.id.txtMonth)
    }
}