package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Flight

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.databinding.FlightAdaptatorBinding
import java.util.*

/**
 * Created by yfouquer on 13/03/18. modified by aescriou later
 */
class FlightAdapter : RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val inflate = DataBindingUtil.inflate<FlightAdaptatorBinding>(
                LayoutInflater.from(parent.context), R.layout.flight_adaptator, parent, false)
        return FlightViewHolder(inflate)

    }

    override fun getItemCount(): Int {
        return UserData.flights.size
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(BR.flight, UserData.flights[position])

        val timeLeft = Date().time - UserData.flights[position].since


        println("Date.time = ${Date().time}")
        println("UserData.flights[position].since = ${UserData.flights[position].since}")



        holder.bind(BR.since, formatDate(timeLeft))

    }

    private fun formatDate(time: Long): String {
        if (time < 0) return " -- s"
        val h = time / 360000
        val min = (time % 360000) / 6000
        val sec = (time % 6000) / 100
        return "" + h + " h " + min + " min " + sec + " s "
    }

    class FlightViewHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {

        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }
    }
}