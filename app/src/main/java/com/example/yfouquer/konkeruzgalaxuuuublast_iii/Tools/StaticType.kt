package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

/**
 * Created by yfouquer on 06/03/18.
 */
object StaticType {
    data class Cost(val btc: Long, val eth: Long, val time: Long)
    data class ShipStats(val damage: Long, val life: Long, val contenance: Long)
    data class DefStats(val damage: Double, val life: Double, val repair: Double)

    abstract class Data {
        abstract val name: String
        abstract val description: String
        abstract val image: String
        abstract val cost: Cost
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


    data class PlanetCoord(val pos: Int, val system: Int)
    data class PlanetRessource(val btc: Long, val eth: Long)

    data class PlanetData(val name: String, val size: Int, val ressource: PlanetRessource,
                          val batiments: List<Pair<Int, Long>>, val coord: PlanetCoord,
                          val defenses: List<Pair<Int, Long>>, val ships: List<Pair<Int, Long>>,
                          val constructionBat: ConstructionBat?,
                          val constructionShip: ConstructionShip?,
                          val constructionDef: ConstructionDef?)

    data class UserInfo(val lastConnnection: Long, val pseudo: String)


    data class UserData(val userInfo: UserInfo, val planets: List<PlanetData>,
                        val technologies: List<Pair<Int, Long>>,
                        val constructionTech: ConstructionTech?)

    abstract class AbstractConstruction {
        abstract val since: Long
    }

    data class ConstructionTech(val id: Long, override val since: Long) : AbstractConstruction()
    data class ConstructionBat(val id: Long, override val since: Long) : AbstractConstruction()

    data class ConstructionDef(val listBuild: List<MutableMap<Int, Long>>, override val since: Long) : AbstractConstruction()
    data class ConstructionShip(val listBuild: List<MutableMap<Int, Long>>, override val since: Long) : AbstractConstruction()



}