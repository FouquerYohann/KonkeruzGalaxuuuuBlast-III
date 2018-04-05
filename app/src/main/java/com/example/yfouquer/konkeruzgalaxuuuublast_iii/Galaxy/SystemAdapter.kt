package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.BR
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Planets.PlanetActionSelector
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.databinding.SystemPlanetViewBinding
import kotlinx.android.synthetic.main.system_planet_view.view.*


class SystemAdapter(val applicationContext: Context, val activity: SystemActivity,
                    val system: Int) : RecyclerView.Adapter<SystemAdapter.SystemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SystemViewHolder {
        val v = DataBindingUtil.inflate<SystemPlanetViewBinding>(
                LayoutInflater.from(parent?.context), R.layout.system_planet_view, parent, false)
        return SystemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: SystemViewHolder?, position: Int) {

        val data = GameData.galaxyMap[Pair(system, position)]
        val to = StaticType.PlanetCoord(position, system)
        val player = data?.component1() ?: ""
        val planetName = data?.component2() ?: ""

        holder?.bind(BR.player, player)
        holder?.bind(BR.planetName, planetName)


        holder?.colonize?.setOnClickListener {
            val builder = AlertDialog.Builder(activity, R.style.Base_Theme_AppCompat_Dialog_Alert)
            val dialogView = LayoutInflater.from(applicationContext).inflate(
                    R.layout.parent_action_selector, null)


            val recycle = dialogView.findViewById<RecyclerView>(R.id.planetRecyclerAction)
            recycle.layoutManager = LinearLayoutManager(applicationContext)
            recycle.adapter = PlanetActionSelector(applicationContext, to, "colonize")

            val mDividerItemDecoration = DividerItemDecoration(recycle.context,
                    RecyclerView.VERTICAL)
            recycle.addItemDecoration(mDividerItemDecoration)

            builder.setView(dialogView)
            builder.setNegativeButton("Cancel", { dialogInterface, _ ->
                Toast.makeText(applicationContext, "CANCEL", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            })
            builder.setPositiveButton("Send", { dialogInterface, _ ->
                Toast.makeText(applicationContext, "WAAAR", Toast.LENGTH_SHORT).show()
                (recycle.adapter as PlanetActionSelector).hasBeenAccepted()
                dialogInterface.dismiss()
            })
            val create = builder.create()
            val display = (applicationContext.getSystemService(
                    Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

            create.window.setLayout((display.width * 0.8).toInt(), (display.height * 0.8).toInt())
            create.show()
        }
        holder?.spy?.setOnClickListener { }
        holder?.attack?.setOnClickListener { }



        holder?.systemId?.text = position.toString()
    }


    class SystemViewHolder(private val v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
        val systemId = v.root.systemId!!
        val colonize = v.root.colonize!!
        val spy = v.root.spy!!
        val attack = v.root.attack!!

        fun bind(id: Int, data: Any) {
            v.setVariable(id, data)
            v.executePendingBindings()
        }
    }
}