package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData.datas
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R

/**
 * Created by yfouquer on 08/03/18.
 */
class ConstructionsFragment: Fragment() {

    companion object {
        fun newInstance(planet:Int,data: String): ConstructionsFragment {
            val arg =Bundle()
            arg.putString("DataType", data)
            arg.putInt("planet",planet)
            val myFragment = ConstructionsFragment()
            myFragment.arguments = arg
            return myFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.construction_fragment, container, false)

        val arg = arguments
        val planet = arg.getInt("planet")
        val listData = datas[arg.get("DataType")]

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = BuildingAdapter(planet, listData!!, activity)
        return view
    }

    fun get(planet:Int, position: Int): ConstructionsFragment {
        return newInstance(planet,datas.toList()[position].first)
    }
}
