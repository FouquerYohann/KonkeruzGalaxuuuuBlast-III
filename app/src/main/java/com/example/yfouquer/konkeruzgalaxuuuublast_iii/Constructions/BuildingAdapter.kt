package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.SuperEnum
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.SuperEnum.*
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R.layout.view_row
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseWrites
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.BuildData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.DefData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.ShipData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.TechData
import kotlinx.android.synthetic.main.view_row.view.*
import java.io.File
import java.io.FileInputStream

class BuildingAdapter(private var planet: Int, private var mDataset: List<StaticType.Data>,
                      private val applicationContext: Context) :
        RecyclerView.Adapter<BuildingAdapter.ConstructionViewHolder>() {

    override fun onBindViewHolder(holder: ConstructionViewHolder, position: Int) {
        val dir = applicationContext.getDir("imagesDir", Context.MODE_PRIVATE)
        val file = File(dir, mDataset[position].image)
        val decodeStream = BitmapFactory.decodeStream(FileInputStream(file))
        val btc = mDataset[position].cost.btc
        val eth = mDataset[position].cost.eth
        holder.image.setImageBitmap(decodeStream)

        val level = UserData.getLevel(planet, SuperEnum.getEnum(mDataset[position]), position)

        holder.text.text = "${mDataset[position].name} \n Level: $level"
        holder.button.text = "BTC : $btc \n ETH : $eth"
        val b = UserData.disableButton[Pair(planet, Companion.getEnum(mDataset[0]))] ?: false
        if (b) holder.button.isEnabled = false

        holder.button.setOnClickListener {
            val superEnum = when (mDataset[0]) {
                is BuildData -> DataBaseWrites.writesToBuilding(planet, BUILDING, position, null)
                is TechData -> DataBaseWrites.writesToBuilding(planet, TECH, position, null)
                is ShipData -> DataBaseWrites.writesToBuilding(planet, SHIP, position, 10)
                is DefData -> DataBaseWrites.writesToBuilding(planet, DEFENSE, position, 10)
                else -> throw IllegalArgumentException("Unknow data type")
            }


        }


    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstructionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(view_row, parent, false)
        return ConstructionViewHolder(v)
    }

    class ConstructionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.textView!!
        val image = view.imageView!!
        val button = view.button!!
    }
}