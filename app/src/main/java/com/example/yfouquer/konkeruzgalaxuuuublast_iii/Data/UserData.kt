package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data

import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType

/**
 * Created by yfouquer on 12/03/18.
 */
object UserData {
    lateinit var uid: String
    lateinit var info: StaticType.UserInfo
    lateinit var planets: MutableList<StaticType.PlanetData>
    lateinit var techs: MutableList<Pair<Int, Long>>

    fun getLevel(planet: Int, data: StaticType.Data, position: Int): Long {

        return when (data) {
            is StaticType.BuildData -> planets[planet].batiments.firstOrNull { it.first == position }?.second
            is StaticType.TechData -> techs.firstOrNull { it.first == position }?.second
            is StaticType.ShipData -> planets[planet].ships.firstOrNull { it.first == position }?.second
            is StaticType.DefData -> planets[planet].defenses.firstOrNull { it.first == position }?.second
            else -> throw IllegalArgumentException("Unknown Type in UserData")
        } ?: 0
    }
}