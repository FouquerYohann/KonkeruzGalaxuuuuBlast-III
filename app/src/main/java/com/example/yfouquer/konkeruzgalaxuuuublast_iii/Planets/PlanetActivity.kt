package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R

/**
 * Created by yfouquer on 12/03/18.
 */
class PlanetActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planet_activity)

//        DataBaseReads.disableButton()

        val recycle = findViewById<RecyclerView>(R.id.planetListView)

        recycle.layoutManager=LinearLayoutManager(this)
        recycle.adapter= PlanetAdapter(UserData.planets,applicationContext)


    }

}
