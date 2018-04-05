package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data

import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType

/**
 * Created by yfouquer on 15/03/18.
 */
enum class SuperEnum {
    BUILDING, SHIP, TECH, DEFENSE;

    companion object {
        fun getEnum(data: StaticType.Data): SuperEnum {
            return when (data) {
                is StaticType.BuildData -> BUILDING
                is StaticType.DefData -> DEFENSE
                is StaticType.TechData -> TECH
                is StaticType.ShipData -> SHIP
                else -> throw IllegalArgumentException("Unknow Data Type")
            }
        }
    }
}