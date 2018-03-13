package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.construction_activity.*


class ConstructionActivity() : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ConstructionsPageAdapter


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.construction_activity)

        val planet = intent.getIntExtra("planet",0)

        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = ConstructionsPageAdapter(planet,supportFragmentManager, "BuildData")
        viewPager.adapter = pagerAdapter

        recyclerTabLayout.setUpWithViewPager(viewPager)
        viewPager.currentItem = 0
    }


}
