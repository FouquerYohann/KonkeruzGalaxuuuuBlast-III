package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.system_explorer.*

/**
 * Created by Yohann on 17/03/2018.
 */
class SystemActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.system_explorer)

        galaxyNumber.setText("5318008",TextView.BufferType.EDITABLE)
        val systemPlanetListView =findViewById<RecyclerView>(R.id.system_planet_list_view)
        systemPlanetListView.layoutManager = LinearLayoutManager(this)
        systemPlanetListView.adapter = SystemAdapter()
    }
}