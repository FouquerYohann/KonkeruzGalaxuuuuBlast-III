package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Constructions

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
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

        holder.bind(BR.item, mDataset[position])


        holder.bind(BR.level, when (mDataset[0]) {
            is BuildData ->
                "Level " + (UserData.planets[planet].batiments.firstOrNull { it.first == position }?.second ?: "0")
            is TechData ->
                "Level " + (UserData.techs.firstOrNull { it.first == position }?.second ?: "0")
            is ShipData ->
                "Number " + (UserData.planets[planet].ships.firstOrNull { it.first == position }?.second ?: "0")
            is DefData ->
                "Number " + (UserData.planets[planet].defenses.firstOrNull { it.first == position }?.second ?: "0")
            else -> throw IllegalArgumentException("Data Type Not Found")
        })


        holder.bind(BR.btc_prix, mDataset[position].cost.btc)
        holder.bind(BR.eth_prix, mDataset[position].cost.eth)

        val dir = applicationContext.getDir("imagesDir", Context.MODE_PRIVATE)
        val file = File(dir, mDataset[position].image)
        val decodeStream = BitmapFactory.decodeStream(FileInputStream(file))
        holder.image.setImageBitmap(decodeStream)


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
        val inflate = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                view_row, parent, false)
        return ConstructionViewHolder(inflate)
    }

    class ConstructionViewHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
        val button = v.root.button!!
        val image = v.root.imageView!!
        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }
    }
}