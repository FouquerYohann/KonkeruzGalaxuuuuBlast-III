package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

/**
 * Created by yfouquer on 06/03/18.
 */
object StaticType {
    data class Cost(val base: CostBase, val level: CostLevel?)
    data class CostBase(val btc: Long, val eth: Long)
    data class CostLevel(val btc: String, val eth: String)
    data class ShipStats(val damage: Long, val life: Long, val contenance: Long)
    data class DefStats(val damage: Double, val life: Double, val repair: Double)

    abstract class Data {
        abstract val name: String
        abstract val description: String
        abstract val image: String
        abstract val cost : Cost
    }

    data class BuildData(override val name: String, override val description: String,
                         override val image: String, override val cost: Cost) : Data()

    data class DefData(override val name: String, override val description: String,
                       override val image: String, val stats: DefStats, override val cost: Cost,
                       val techs: List<Pair<Int, Long>>) : Data()

    data class ShipData(override val name: String, override val description: String,
                        override val image: String, val stats: ShipStats, override val cost: Cost,
                        val techs: List<Pair<Int, Long>>) : Data()

    data class TechData(override val name: String, override val description: String,
                        override val image: String, override val cost: Cost) : Data()

}