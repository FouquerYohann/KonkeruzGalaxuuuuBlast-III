package com.example.yfouquer.konkeruzgalaxuuuublast_iii.ActionSelection

import android.content.Context

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseWrites
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.databinding.ActionSelectionItemBinding
import kotlinx.android.synthetic.main.action_selection_item.view.*
import java.io.File
import java.io.FileInputStream

/**
 * Created by yfouquer on 04/04/18.
 */
class VesselActionAdapter(private val applicationContext: Context, val to: StaticType.PlanetCoord,
                          private val objectif: String, private val planet: Int) :
        RecyclerView.Adapter<VesselActionAdapter.VesselViewHolder>() {

    val mapVesselValue = mutableMapOf<String, Int>()

    fun gogo() {
        DataBaseWrites.writesToLaunch(to, objectif, planet, mapVesselValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VesselViewHolder {
        val inflate = DataBindingUtil.inflate<ActionSelectionItemBinding>(
                LayoutInflater.from(parent?.context), R.layout.action_selection_item, parent, false)

        return VesselViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return UserData.planets[planet].ships.size
    }


    override fun onBindViewHolder(holder: VesselViewHolder, position: Int) {

        val currentPairShip = UserData.planets[planet].ships[position]
        holder.bind(BR.ship, GameData.ships[currentPairShip.first])
        holder.bind(BR.numVessel, currentPairShip.second.toInt())

        holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var cur: Int = 0
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                holder.nbVessel.text = progress.toString()
                cur = progress
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mapVesselValue[currentPairShip.first.toString()] = cur
            }
        })

        val dir = applicationContext.getDir("imagesDir", Context.MODE_PRIVATE)
        val file = File(dir, GameData.ships[currentPairShip.first].image)
        val decodeStream = BitmapFactory.decodeStream(FileInputStream(file))
        holder.imageVessel.setImageBitmap(decodeStream)
    }

    class VesselViewHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
        val imageVessel = v.root.imageVessel!!
        val nbVessel = v.root.vessel_number!!

        val seekBar = v.root.vesselSeekBar!!
        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }

    }

}