package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.ActionSelection.VesselActionAdapter
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.databinding.ParentActionSelectorBinding
import kotlinx.android.synthetic.main.parent_action_selector.view.*

/**
 * Created by yfouquer on 13/03/18. modified by aescriou later
 */
class PlanetActionSelector(val applicationContext: Context, val to: StaticType.PlanetCoord,
                           val objectif: String) :
        RecyclerView.Adapter<PlanetActionSelector.PlanetSelectionHolder>() {

    private var expandedPosition: Int = -1

    private var holderSet = mutableSetOf<PlanetSelectionHolder>()

    fun hasBeenAccepted() {
        holderSet.forEach { (it.recyclerView.adapter as VesselActionAdapter).gogo() }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetSelectionHolder {
        val inflate = DataBindingUtil.inflate<ParentActionSelectorBinding>(
                LayoutInflater.from(parent.context), R.layout.parent_action_selector, parent, false)

        return PlanetSelectionHolder(inflate)
    }

    override fun getItemCount(): Int {
        return UserData.planets.size
    }

    override fun onBindViewHolder(holder: PlanetSelectionHolder, position: Int) {
        holderSet.add(holder)
        holder.bind(BR.planetParentSelection, UserData.planets[position])

        holder.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        holder.recyclerView.adapter = VesselActionAdapter(applicationContext, to, objectif,
                position)

    }

    class PlanetSelectionHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
        val recyclerView = v.root.planetRecyclerAction!!

        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }

    }
}