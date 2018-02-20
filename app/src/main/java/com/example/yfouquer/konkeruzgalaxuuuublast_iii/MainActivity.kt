package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = BuildingAdapter(data)


    }

    companion object {
        data class DataBuild(val name: String, val imageRef: Int, val metPrice: Int, val cristPrice: Int)

        val data = listOf(
                DataBuild("Mine de metal", R.drawable.mine_de_metal, 100, 0),
                DataBuild("Mine de cristal", R.drawable.mine_de_cristal, 200, 300))


    }


}
