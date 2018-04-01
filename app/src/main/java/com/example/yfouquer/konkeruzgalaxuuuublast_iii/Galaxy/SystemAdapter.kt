package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.databinding.SystemPlanetViewBinding
import kotlinx.android.synthetic.main.system_planet_view.view.*

/**
 * Created by Yohann on 17/03/2018.
 */

class SystemAdapter(val system: Int) : RecyclerView.Adapter<SystemAdapter.SystemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SystemViewHolder {
        val v = DataBindingUtil.inflate<SystemPlanetViewBinding>(LayoutInflater.from(parent?.context),
                R.layout.system_planet_view, parent, false)
        return SystemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: SystemViewHolder?, position: Int) {

        val data = GameData.galaxyMap[Pair(system, position)]
        val player = data?.component1() ?: ""
        val planetName = data?.component2() ?: ""

        holder?.bind(BR.player,player)
        holder?.bind(BR.planetName,planetName)

        holder?.systemId?.text = position.toString()
        holder?.systemButton1?.text = "A"
        holder?.systemButton2?.text = "B"
        holder?.systemButton3?.text = "C"
    }


    class SystemViewHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
        val systemId = v.root.systemId!!
        val systemButton1 = v.root.systemButton1!!
        val systemButton2 = v.root.systemButton2!!
        val systemButton3 = v.root.systemButton3!!


        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }
    }
}