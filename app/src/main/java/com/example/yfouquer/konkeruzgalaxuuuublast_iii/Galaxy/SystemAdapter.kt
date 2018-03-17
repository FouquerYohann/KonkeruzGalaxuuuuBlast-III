package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import kotlinx.android.synthetic.main.system_planet_view.view.*

/**
 * Created by Yohann on 17/03/2018.
 */

class SystemAdapter :RecyclerView.Adapter<SystemAdapter.SystemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SystemViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.system_planet_view, parent, false)
        return SystemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: SystemViewHolder?, position: Int) {
        holder?.systemId?.text =position.toString()
        holder?.ownerName?.text ="ownerName"+position
        holder?.planetName?.text ="planetName"+position
        holder?.systemButton1?.text ="A"
        holder?.systemButton2?.text ="B"
        holder?.systemButton3?.text ="C"
    }


    class SystemViewHolder(v:View) : RecyclerView.ViewHolder(v) {
        val systemId = v.systemId!!
        val ownerName = v.ownerName!!
        val planetName = v.planetName!!
        val systemButton1 = v.systemButton1!!
        val systemButton2 = v.systemButton2!!
        val systemButton3 = v.systemButton3!!

    }
}