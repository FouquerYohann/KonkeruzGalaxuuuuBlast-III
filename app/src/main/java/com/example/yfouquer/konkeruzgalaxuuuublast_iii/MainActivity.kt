package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseTools
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = BuildingAdapter(DataBaseTools.testing,applicationContext)


    }



}
