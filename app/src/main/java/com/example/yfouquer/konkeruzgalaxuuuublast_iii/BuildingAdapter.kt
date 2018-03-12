package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R.layout.view_row
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import kotlinx.android.synthetic.main.view_row.view.*
import java.io.File
import java.io.FileInputStream

class BuildingAdapter(private var planet: Int,private var mDataset: List<StaticType.Data>, private val applicationContext: Context) :
        RecyclerView.Adapter<BuildingAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dir = applicationContext.getDir("imagesDir",Context.MODE_PRIVATE)
        val file = File(dir, mDataset[position].image)
        val decodeStream = BitmapFactory.decodeStream(FileInputStream(file))
        val btc = mDataset[position].cost.base.btc
        val eth = mDataset[position].cost.base.eth
        holder.image.setImageBitmap(decodeStream)

        val level = UserData.getLevel(planet, mDataset[position], position)

        holder.text.text = "${mDataset[position].name} \n Level: $level"
        holder.button.text = "BTC : $btc \n ETH : $eth"
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(view_row, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.textView!!
        val image = view.imageView!!
        val button = view.button!!
    }
}