package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Flight.FlightActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy.SystemActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseReads
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Utilities.OnSwipeListener
import kotlinx.android.synthetic.main.planet_activity.*

/**
 * Created by yfouquer on 12/03/18.
 */
class PlanetActivity : AppCompatActivity() {

    lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planet_activity)

        DataBaseReads.autoRefreshUser(UserData.uid)

        gestureDetector = GestureDetectorCompat(this, OnSwipeListener(this))

        val recycle = findViewById<RecyclerView>(R.id.planetListView)

        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = PlanetAdapter(UserData.planets, applicationContext)

        val mDividerItemDecoration = DividerItemDecoration(recycle.context, RecyclerView.VERTICAL)
        recycle.addItemDecoration(mDividerItemDecoration)

        refresh.setOnClickListener {
            DataBaseReads.userData(UserData.uid)
        }

        galaxy_explorer.setOnClickListener {
            startActivity(Intent(applicationContext, SystemActivity::class.java))
        }
        flights_explorer.setOnClickListener {
            startActivity(Intent(applicationContext, FlightActivity::class.java))
        }
    }

    override fun onBackPressed() {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

}
