package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData.datas
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseTools
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import kotlinx.android.synthetic.main.my_fragment.*

/**
 * Created by yfouquer on 08/03/18.
 */
class MyFragment: Fragment() {

    companion object {
        fun newInstance(data: String):MyFragment{
            val arg =Bundle()
            arg.putString("DataType", data)
            val myFragment = MyFragment()
            myFragment.arguments = arg
            return myFragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.my_fragment, container, false)

        val arg = arguments
        val listData = datas[arg.get("DataType")]

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        //TODO add planet number
        recyclerView.adapter = BuildingAdapter(0, listData!!,activity)
        return view
    }

    fun get(position: Int): MyFragment {
        return newInstance(datas.toList()[position].first)
    }
}
