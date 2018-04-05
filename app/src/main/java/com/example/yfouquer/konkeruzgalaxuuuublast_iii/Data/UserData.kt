package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data

import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType

object UserData {
    lateinit var uid: String
    lateinit var info: StaticType.UserInfo
    lateinit var planets: MutableList<StaticType.PlanetData>
    lateinit var techs: MutableList<Pair<Int, Long>>
    lateinit var flights: MutableList<StaticType.FlightData>

    var disableButton: MutableMap<Pair<Int, SuperEnum>, Boolean> = hashMapOf()

    fun getLevel(planet: Int, data: SuperEnum, position: Int): Long {
        return when (data) {
            SuperEnum.BUILDING -> planets[planet].batiments.firstOrNull { it.first == position }?.second
            SuperEnum.TECH -> techs.firstOrNull { it.first == position }?.second
            SuperEnum.SHIP -> planets[planet].ships.firstOrNull { it.first == position }?.second
            SuperEnum.DEFENSE -> planets[planet].defenses.firstOrNull { it.first == position }?.second
        } ?: 0
    }
}