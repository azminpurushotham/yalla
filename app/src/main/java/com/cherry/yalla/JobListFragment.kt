package com.cherry.yalla

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class JobListFragment : Fragment() {

    private val TAG: String = "JobListFragment"
    val months: ArrayList<String> = ArrayList()
    val dates: ArrayList<String> = ArrayList()
    lateinit var rvMonths: RecyclerView
    lateinit var rvDate: RecyclerView
    lateinit var rvJobs: RecyclerView
    lateinit var adapter: MonthAdapter
    lateinit var datesAdapter: DatesAdapter
    lateinit var jobsAdapter: JobsAdapter

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

        for (i in 1..30) {
            dates.add(i.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        print(" onCreateView ")
        Log.v("onCreateView", TAG)
        val view = inflater.inflate(R.layout.fragment_joblist, container, false)
        rvMonths = view.findViewById(R.id.rvMonths)
        rvDate = view.findViewById(R.id.rvDate)
        rvJobs = view.findViewById(R.id.rvJobs)

        adapter = MonthAdapter(months)
        rvMonths.adapter = adapter

        print(" Size -->> ${dates.size}")
        Log.v(" Size -->> ${dates.size}", TAG)
        datesAdapter = DatesAdapter(dates)
        rvDate.adapter = datesAdapter

        jobsAdapter = JobsAdapter(months)
        rvJobs.adapter = jobsAdapter

        return view
    }
}