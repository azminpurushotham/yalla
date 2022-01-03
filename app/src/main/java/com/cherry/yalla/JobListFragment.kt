package com.cherry.yalla

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cherry.yalla.screens.job.JobActivity
import com.cherry.yalla.screens.order.MyJobActivity
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

    lateinit var title: TextView
    lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        months.add("January")
        months.add("Febuary")
        months.add("March")
        months.add("April")
        months.add("May")
        months.add("June")
        months.add("July")
        months.add("August")
        months.add("Septmber")
        months.add("October")
        months.add("November")
        months.add("December")

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
        title = view.findViewById(R.id.title)
        btnBack = view.findViewById(R.id.btnBack)

        adapter = MonthAdapter(months)
        rvMonths.adapter = adapter

        print(" Size -->> ${dates.size}")
        Log.v(" Size -->> ${dates.size}", TAG)
        datesAdapter = DatesAdapter(dates)
        rvDate.adapter = datesAdapter

        jobsAdapter = JobsAdapter(months,this::onClicked)
        rvJobs.adapter = jobsAdapter

        title.text = "My Job"

        return view
    }


    private fun onClicked(){
//        findNavController().navigate(R.id.itemsToReturnFragment)
        startActivity(Intent(requireActivity(), JobActivity::class.java))
    }
}