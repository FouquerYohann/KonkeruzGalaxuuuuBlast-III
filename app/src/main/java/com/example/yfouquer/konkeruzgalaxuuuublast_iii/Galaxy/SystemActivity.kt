package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SeekBar
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.system_explorer.*

/**
 * Created by Yohann on 17/03/2018.
 */
class SystemActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    var system: Int = 0

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        galaxyNumber.text = progress.toString()
        system = progress
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        val systemPlanetListView = findViewById<RecyclerView>(R.id.system_planet_list_view)
        systemPlanetListView.adapter = SystemAdapter(applicationContext, this, system)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.system_explorer)

        val curSystem = intent.getIntExtra("currentSystem", 0)

        seekBar.setOnSeekBarChangeListener(this)
        seekBar.progress = curSystem

        val systemPlanetListView = findViewById<RecyclerView>(R.id.system_planet_list_view)
        systemPlanetListView.layoutManager = LinearLayoutManager(this)
        systemPlanetListView.adapter = SystemAdapter(applicationContext, this, system)

        val mDividerItemDecoration = DividerItemDecoration(systemPlanetListView.context,
                RecyclerView.VERTICAL)
        systemPlanetListView.addItemDecoration(mDividerItemDecoration)
    }
}