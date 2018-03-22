package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions.ConstructionActivity
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.SuperEnum
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.calculEngine
import kotlinx.android.synthetic.main.planet_view.view.*
import java.util.*

/**
 * Created by yfouquer on 13/03/18. modified by aescriou later
 */
class PlanetAdapter(private var planet: MutableList<StaticType.PlanetData>, val applicationContext: Context) :
        RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    var expandedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.planet_view, parent, false)
        return PlanetViewHolder(v)
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

        val isExpanded = position == expandedPosition
        holder.toHide.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded
        holder.itemView.setOnClickListener({
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        })
        val buildingId = UserData.planets[position].constructionBat?.id?.toInt()
        if (buildingId != null) {
            val timeLeft = Date().time - UserData.planets[position].constructionBat!!.since
            val levelBat = UserData.getLevel(position, SuperEnum.BUILDING, buildingId)
            val realTime = calculEngine.timeBaTech(levelBat.toInt(), UserData.getLevel(position, SuperEnum.BUILDING, 3).toInt(), GameData.buildings[buildingId].cost.time.toInt())
            val time = realTime - timeLeft
            val str = GameData.buildings[buildingId].name + " lvl " + (levelBat + 1) + ": " + this.formatDate(time)
            holder.battencourt.text = str
        }
        holder.goToPlanet.setOnClickListener {
            val intent = Intent(applicationContext, ConstructionActivity::class.java).putExtra(
                    "planet", position)

            startActivity(applicationContext, intent, null)

        }

    }

    private fun formatDate(time: Long): String {
        if(time<0)return " -- s"
        val h = time / 360000
        val min = (time % 360000) / 6000
        val sec = (time % 6000) / 100
        return "" + h + " h " + min + " min " + sec + " s "
    }

    class PlanetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.title_planet!!
        val image = v.planetView!!
        val eth = v.eth!!
        val btc = v.btc!!
        val toHide = v.toHide!!
        val goToPlanet = v.goToPlanet!!
        val battencourt = v.Battencourt!!
    }
}