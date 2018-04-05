package com.example.yfouquer.konkeruzgalaxuuuublast_iii.unused

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData

class PlanetsPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return PlanetsFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return UserData.planets.size
    }

}
