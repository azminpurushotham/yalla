package com.cherry.yalla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class JobListFragment : Fragment() {

    val months: ArrayList<String> = ArrayList()
    lateinit var rvMonths: RecyclerView;
    lateinit var adapter: MonthAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        months.add("Jan")
        months.add("Feb")
        months.add("Mar")
        months.add("Apr")
        months.add("May")
        months.add("Jun")
        months.add("Jul")
        months.add("Aug")
        months.add("Sep")
        months.add("Oct")
        months.add("Nov")
        months.add("Dec")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        rvMonths = view.findViewById(R.id.rvMonths)
        adapter = MonthAdapter(months)
        return view
    }
}