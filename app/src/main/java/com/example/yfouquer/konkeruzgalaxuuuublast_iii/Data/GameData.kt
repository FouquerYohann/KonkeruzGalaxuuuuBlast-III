package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data

import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.BuildData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.Data
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.DefData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.ShipData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.TechData

/**
 * Created by yfouquer on 08/03/18.
 */
object GameData {

    var buildings = mutableListOf<BuildData>()
    var defenses = mutableListOf<DefData>()
    var techs = mutableListOf<TechData>()
    var ships = mutableListOf<ShipData>()

    var galaxyMap = mutableMapOf<Pair<Int, Int>,String>()

    var datas = hashMapOf("BuildData" to buildings, "DefData" to defenses,
            "TechData" to techs, "ShipData" to ships)


    fun add(data: Data) {
        when (data) {
            is BuildData -> buildings.add(data)
            is DefData -> defenses.add(data)
            is TechData -> techs.add(data)
            is ShipData -> ships.add(data)
        }
    }
}