package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: GamePageAdapter


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = GamePageAdapter(supportFragmentManager, "BuildData")
        viewPager.adapter=pagerAdapter

        recyclerTabLayout.setUpWithViewPager(viewPager)
        viewPager.currentItem=0
    }



}
