package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy.SystemActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseWrites
import kotlinx.android.synthetic.main.construction_activity.*


class ConstructionActivity() : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ConstructionsPageAdapter


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.construction_activity)
        val binding = setContentView<ViewDataBinding>(this, R.layout.construction_activity)

        val planet = intent.getIntExtra("planet", 0)
        binding.setVariable(BR.planet, UserData.planets[planet])

        goToSystem.setOnClickListener {
            val intent = Intent(this, SystemActivity::class.java).putExtra("currentSystem",
                    UserData.planets[planet].coord.system)
            startActivity(intent)
        }

        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = ConstructionsPageAdapter(planet, supportFragmentManager, "BuildData")
        viewPager.adapter = pagerAdapter



        recyclerTabLayout.setUpWithViewPager(viewPager)
        viewPager.currentItem = 0

        refresher.setOnClickListener {
            DataBaseWrites.setMaj()

        }
    }


}
