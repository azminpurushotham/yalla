package com.cherry.yalla

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class ItemsToReturnFragment : Fragment() {

    private val TAG: String = "ItemsToReturnFragment"
    val months: ArrayList<String> = ArrayList()
    lateinit var rvMonths: RecyclerView
    lateinit var adapter: ItemsToReturnAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        months.add("Jan")
        months.add("Feb")
        months.add("Mar")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("onCreateView", TAG)
        val view = inflater.inflate(R.layout.fragment_itemstoreturn, container, false)
        rvMonths = view.findViewById(R.id.rvMonths)
        adapter = ItemsToReturnAdapter(months)
        rvMonths.adapter = adapter
        return view
    }

}