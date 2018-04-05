package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Flight

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.flight_activity.*

class FlightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flight_activity)

        flightRecycler.layoutManager = LinearLayoutManager(applicationContext)
        flightRecycler.adapter = FlightAdapter()

        val mDividerItemDecoration = DividerItemDecoration(flightRecycler.context,
                RecyclerView.VERTICAL)
        flightRecycler.addItemDecoration(mDividerItemDecoration)

    }
}