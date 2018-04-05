package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData.datas

/**
 * Created by yfouquer on 08/03/18.
 */
class ConstructionsPageAdapter(val planet: Int, fragmentManager: FragmentManager,
                               private val gameData: String) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return ConstructionsFragment.newInstance(planet, gameData).get(planet, position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return datas.toList()[position].first
    }

    override fun getCount(): Int {
        return datas.size
    }


}