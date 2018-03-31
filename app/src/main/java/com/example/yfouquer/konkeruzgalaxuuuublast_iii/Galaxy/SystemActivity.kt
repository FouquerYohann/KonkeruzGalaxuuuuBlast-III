package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SeekBar
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.system_explorer.*

/**
 * Created by Yohann on 17/03/2018.
 */
class SystemActivity :AppCompatActivity(),SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        galaxyNumber.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.system_explorer)


        seekBar.setOnSeekBarChangeListener(this)

        galaxyNumber
        val systemPlanetListView =findViewById<RecyclerView>(R.id.system_planet_list_view)
        systemPlanetListView.layoutManager = LinearLayoutManager(this)
        systemPlanetListView.adapter = SystemAdapter()
    }
}