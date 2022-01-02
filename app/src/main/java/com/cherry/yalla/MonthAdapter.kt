package com.cherry.yalla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MonthAdapter(private val list: List<String>) :
    RecyclerView.Adapter<MonthAdapter.ViewHolder>() {
    var selPosition = 0;

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_month, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtMonth.text = list[position]
        holder.itemView.setOnClickListener {
            selPosition = position
            notifyDataSetChanged()
        }

        if (selPosition == position) {
            holder.btnDot.visibility = View.VISIBLE
            holder.txtMonth.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.shape_primary_button)
            holder.txtMonth.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }else{
            holder.btnDot.visibility = View.INVISIBLE
            holder.txtMonth.background = null
            holder.txtMonth.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtMonth: TextView = itemView.findViewById(R.id.txtMonth)
        val btnDot: Button = itemView.findViewById(R.id.btnDot)
    }
}