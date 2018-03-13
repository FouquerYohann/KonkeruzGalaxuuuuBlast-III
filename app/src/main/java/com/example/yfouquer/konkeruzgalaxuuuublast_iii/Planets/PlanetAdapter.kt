package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions.ConstructionActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import kotlinx.android.synthetic.main.planet_activity.view.*

/**
 * Created by yfouquer on 13/03/18.
 */
class PlanetAdapter(private var planet: MutableList<StaticType.PlanetData>, val applicationContext: Context) :
        RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.planet_activity, parent, false)
        return PlanetViewHolder(v,applicationContext)
    }

    override fun getItemCount(): Int {
        return UserData.planets.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.name.text = planet[position].name
        holder.btc.text = planet[position].ressource.btc.toString()
        holder.eth.text = planet[position].ressource.eth.toString()
        val imageId = when (position) {
            0 -> R.drawable.planet_0
            1 -> R.drawable.planet_1
            2 -> R.drawable.planet_2
            else -> R.drawable.antoine_mini
        }
        holder.image.setImageDrawable(applicationContext.resources.getDrawable(imageId, null))
    }

    class PlanetViewHolder(v: View,val applicationContext: Context) : RecyclerView.ViewHolder(v),View.OnClickListener {
        val name = v.title_planet!!
        val image = v.planetView!!
        val eth = v.eth!!
        val btc = v.btc!!

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View) {
            val intent = Intent(applicationContext, ConstructionActivity::class.java)
            intent.putExtra("planet",adapterPosition)
            startActivity(applicationContext,intent,null)
        }


    }
}