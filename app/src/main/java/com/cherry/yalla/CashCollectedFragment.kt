package com.cherry.yalla

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cherry.yalla.screens.job.AcceptedJobActivity
import com.cherry.yalla.screens.job.JobActivity
import com.cherry.yalla.screens.order.MyJobActivity
import kotlin.math.log

class CashCollectedFragment : Fragment() {

    private val TAG: String = "CashCollectedFragment"
    val months: ArrayList<String> = ArrayList()
    val dates: ArrayList<String> = ArrayList()
    lateinit var rvMonths: RecyclerView
    lateinit var imgFilter: ImageView
    lateinit var rvDate: RecyclerView
    lateinit var rvJobs: RecyclerView
    lateinit var adapter: MonthAdapter
    lateinit var datesAdapter: DatesAdapter
    lateinit var jobsAdapter: CashColletedAdapter

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
        val view = inflater.inflate(R.layout.fragment_cashcollected, container, false)
        rvMonths = view.findViewById(R.id.rvMonths)
        rvDate = view.findViewById(R.id.rvDate)
        rvJobs = view.findViewById(R.id.rvJobs)
        title = view.findViewById(R.id.title)
        btnBack = view.findViewById(R.id.btnBack)
        imgFilter = view.findViewById(R.id.imgFilter)

        adapter = MonthAdapter(months)
        rvMonths.adapter = adapter

        print(" Size -->> ${dates.size}")
        Log.v(" Size -->> ${dates.size}", TAG)
        datesAdapter = DatesAdapter(dates)
        rvDate.adapter = datesAdapter

        jobsAdapter = CashColletedAdapter(months, this::onClicked)
        rvJobs.adapter = jobsAdapter

        imgFilter.setOnClickListener {
            showFilterDialogue()
        }

        title.text = "Cash Collected"

        return view
    }

    private fun showFilterDialogue() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialogue_filter)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }


    private fun onClicked() {
//        findNavController().navigate(R.id.itemsToReturnFragment)
        startActivity(Intent(requireActivity(), AcceptedJobActivity::class.java))
    }
}