package com.cherry.yalla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DatesAdapter(private val list: List<String>) :
    RecyclerView.Adapter<DatesAdapter.ViewHolder>() {
    var selPosition = 0;

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_date, parent, false)

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
            holder.txtMonth.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.primary))
            holder.txtMonth.textSize = 20.0F
        }else{
            holder.txtMonth.setTextColor(ContextCompat.getColor(holder.itemView.context,R.color.black))
            holder.txtMonth.textSize = 15.0F
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtMonth: TextView = itemView.findViewById(R.id.txtMonth)
    }
}