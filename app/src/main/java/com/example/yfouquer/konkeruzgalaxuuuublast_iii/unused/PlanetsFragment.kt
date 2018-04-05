package com.example.yfouquer.konkeruzgalaxuuuublast_iii.unused

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R

class PlanetsFragment : Fragment() {

    companion object {
        fun newInstance(planet: Int): PlanetsFragment {
            val planetsFragment = PlanetsFragment()
            val arg = Bundle()
            arg.putInt("planet", planet)
            planetsFragment.arguments = arg
            return planetsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.planet_view, container, false)
        val planet = arguments.getInt("planet")
        val text = view.findViewById<TextView>(R.id.title_planet)
        val btc = view.findViewById<TextView>(R.id.btc)
        val eth = view.findViewById<TextView>(R.id.eth)
        val planetView = view.findViewById<ImageView>(R.id.planetView)


        text.text = UserData.planets[planet].name
        btc.text = UserData.planets[planet].ressource.btc.toString()
        eth.text = UserData.planets[planet].ressource.eth.toString()

        val imageId = when (planet) {
            0 -> R.drawable.planet_0
            1 -> R.drawable.planet_1
            2 -> R.drawable.planet_2
            else -> R.mipmap.antoine_mini
        }

        planetView.setImageDrawable(resources.getDrawable(imageId, null))

        return view
    }
}
