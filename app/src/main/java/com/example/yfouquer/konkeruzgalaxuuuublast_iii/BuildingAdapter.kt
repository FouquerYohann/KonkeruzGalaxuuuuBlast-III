package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_row.view.*

class BuildingAdapter(private var mDataset: List<Pair<String,Int>>) : RecyclerView.Adapter<BuildingAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = mDataset[position].first
        holder.image.setImageResource(mDataset[position].second)
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_row, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val text = view.textView
        val image = view.imageView
    }
}