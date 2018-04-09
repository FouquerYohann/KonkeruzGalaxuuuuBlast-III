package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

import android.content.Context
import android.util.Log
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.SuperEnum
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.BuildData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.Cost
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.DefData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.DefStats
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.ShipData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.ShipStats
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.TechData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream

object DataBaseReads {

    var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDataBaseReference: DatabaseReference = mDataBase.reference
    var mStorageInst: FirebaseStorage = FirebaseStorage.getInstance()

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun nameDescImage(ds: DataSnapshot): Triple<String, String, String> {
        return Triple(ds.child("name").value as String, ds.child("description").value as String,
                ds.child("image").value as String)
    }

    fun makeDefStat(ds: DataSnapshot): DefStats {
        return DefStats((ds.child("damage").value as Long).toDouble(),
                (ds.child("life").value as Long).toDouble(), ds.child("repair").value as Double)
    }

    fun makeShipStat(ds: DataSnapshot): ShipStats {
        return ShipStats(ds.child("damage").value as Long, ds.child("life").value as Long,
                ds.child("contenance").value as Long)

    }

    private fun dowloadImage(applicationContext: Context, image: String,
                             referenceFromUrl: StorageReference) {
        val dir = applicationContext.getDir("imagesDir", Context.MODE_PRIVATE)
        if (!dir.exists()) {
            dir.mkdir()
        }
        val imgFile = File(dir, image)
        if (imgFile.exists() && imgFile.lastModified() + 24 * 3600 * 1000 > System.currentTimeMillis()) {
            return
        }
        referenceFromUrl.getBytes(1024 * 1024).addOnSuccessListener { bytes ->
            FileOutputStream(imgFile).write(bytes)
            Log.i("DownImage", image + " finished at " + imgFile.canonicalFile)
        }.addOnFailureListener { exception ->
            Log.d("Download Images", exception.message)
        }
    }

    fun buildingData(applicationContext: Context) {
        mDataBaseReference.child("game/batiment").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach({ ds ->
                    run {
                        val (name, desc, image) = nameDescImage(ds)
                        val referenceFromUrl = mStorageInst.getReference(image)
                        val btc = ds.child("cost/btc").value as Long
                        val eth = ds.child("cost/eth").value as Long
                        val time = ds.child("cost/time").value as Long

                        dowloadImage(applicationContext, image, referenceFromUrl)
                        GameData.add(BuildData(name, desc, image, Cost(btc, eth, time)))
                    }
                })
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }
        })
    }

    fun defenseData(applicationContext: Context) {
        mDataBaseReference.child("game/defenses").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { ds ->
                    val (name, desc, image) = nameDescImage(ds)
                    val imageRef = mStorageInst.getReference(image)
                    val btc = ds.child("cost/btc").value as Long
                    val eth = ds.child("cost/eth").value as Long
                    val time = ds.child("cost/time").value as Long // TODO doesn't exist
                    val defStats = makeDefStat(ds.child("stats"))
                    val techs = ds.child("techs").children.map {
                        Pair(it.key.toInt(), it.value as Long)
                    }.toCollection(mutableListOf())
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(DefData(name, desc, image, defStats, Cost(btc, eth, time), techs))
                }
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }
        })
    }

    fun techData(applicationContext: Context) {
        mDataBaseReference.child("game/techs").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { ds ->
                    val (name, desc, image) = nameDescImage(ds)
                    val imageRef = mStorageInst.getReference(image)
                    val btc = ds.child("cost/btc").value as Long
                    val eth = ds.child("cost/eth").value as Long
                    val time = ds.child("cost/time").value as Long
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(TechData(name, desc, image, Cost(btc, eth, time)))
                }
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }
        })
    }

    fun shipData(applicationContext: Context) {
        mDataBaseReference.child("game/ships").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { ds ->
                    val (name, desc, image) = nameDescImage(ds)
                    val imageRef = mStorageInst.getReference(image)
                    val btc = ds.child("cost/btc").value as Long
                    val eth = ds.child("cost/eth").value as Long
                    val time = ds.child("cost/time").value as Long
                    val stats = makeShipStat(ds.child("stats"))
                    val techs = ds.child("techs").children.map {
                        Pair(it.key.toInt(), it.value as Long)
                    }.toCollection(mutableListOf())
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(ShipData(name, desc, image, stats, Cost(btc, eth, time), techs))
                }
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")

            }
        })
    }


    fun userData(userId: String) {
        println("USER DATA UPDATED !!!!")

        mDataBaseReference.child("users/$userId").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val info = infofrom(dataSnapshot.child("info"))
                val planets = planets(dataSnapshot.child("planets"))
                val techs = dataSnapshot.child("technologies").children.map {
                    Pair(it.key.toInt(), it.value as Long)
                }.toCollection(mutableListOf())
                UserData.info = info
                UserData.planets = planets
                UserData.techs = techs
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }
        })
    }

    fun autoRefreshUser(userId: String) {
        mDataBaseReference.child("users/$userId").addValueEventListener(object :
                ValueEventListener {
            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }

            override fun onDataChange(p0: DataSnapshot?) {
                userData(userId)
                GalaxyInfo()
                flightsInfo()
            }
        })
    }

    private fun planets(child: DataSnapshot): MutableList<StaticType.PlanetData> {
        return child.children.map {
            val name = it.child("name").value.toString()
            val batiments = it.child("batiments").children.map {
                Pair(it.key.toInt(), it.value as Long)
            }.toCollection(mutableListOf())
            val planetCoord = StaticType.PlanetCoord((it.child("coord/pos").value as Long).toInt(),
                    (it.child("coord/sys").value as Long).toInt())
            val defenses = it.child("defenses").children.map {
                Pair(it.key.toInt(), it.value as Long)
            }.toCollection(mutableListOf())
            val resource = StaticType.PlanetRessource(it.child("ressources/btc").value as Long,
                    it.child("ressources/eth").value as Long)
            val ships = it.child("ships").children.map {
                Pair<Int, Long>(it.key.toInt(), it.value as Long)
            }.toCollection(mutableListOf())
            val size = (it.child("size").value as Long).toInt()
            var constructionShips: StaticType.ConstructionShip? = null
            var constructionBat: StaticType.ConstructionBat? = null
            var constructionDef: StaticType.ConstructionDef? = null
            it.child("construction").children.map {
                val abstractConstruction = when (it.key) {
                    "batiments" -> StaticType.ConstructionBat(it.child("id").value as Long,
                            it.child("since").value as Long)
                    "defenses" -> StaticType.ConstructionDef(it.child("list").children.map {
                        it.children.map {
                            Pair<Int, Long>(it.key.toInt(), it.value as Long)
                        }.toMap(HashMap<Int, Long>())
                    }.toList(), it.child("since").value as Long)
                    "ships" -> StaticType.ConstructionShip(it.child("list").children.map {
                        it.children.map {
                            Pair<Int, Long>(it.key.toInt(), it.value as Long)
                        }.toMap(HashMap<Int, Long>())
                    }.toList(), it.child("since").value as Long)
                    else -> null
                }

                when (abstractConstruction) {
                    is StaticType.ConstructionShip -> constructionShips = abstractConstruction
                    is StaticType.ConstructionBat -> constructionBat = abstractConstruction
                    is StaticType.ConstructionDef -> constructionDef = abstractConstruction
                }
            }
            StaticType.PlanetData(name, size, resource, batiments, planetCoord, defenses, ships,
                    constructionBat, constructionShips, constructionDef)
        }.toCollection(mutableListOf())
    }


    fun flightsInfo() {
        mDataBaseReference.child("flights/list").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                try {
                    val mutableListOf = mutableListOf<StaticType.FlightData>()
                    ds.children.forEach {
                        if (!(it.key as String).equals("toFill")) {
                            var position = (it.child("dest/pos").value as Long).toInt()
                            var system = (it.child("dest/sys").value as Long).toInt()
                            val to = StaticType.PlanetCoord(position, system)
                            position = (it.child("origin/pos").value as Long).toInt()
                            system = (it.child("origin/sys").value as Long).toInt()
                            val from = StaticType.PlanetCoord(position, system)

                            val UserPlanetCoord = UserData.planets.map { it.coord }.toCollection(
                                    mutableListOf())

                            if (UserPlanetCoord.contains(to) || UserPlanetCoord.contains(from)) {
                                val captain = it.child("captainName").value as String
                                val since = it.child("since").value as Long
                                val objectif = it.child("goal").value as String

                                val mapVessel = it.child("ships").children.map {
                                    Pair(it.key.toInt(), (it.value as Long).toInt())
                                }.toMap(mutableMapOf())
                                mutableListOf.add(
                                        StaticType.FlightData(from, to, objectif, mapVessel,
                                                captain, since))
                            }
                        }
                    }
                    UserData.flights = mutableListOf
                } catch (e: UninitializedPropertyAccessException) {
                    println("Unitialize property retrying after delay")
                    Thread.sleep(100)
                    flightsInfo()
                }
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }
        })
    }

    private fun infofrom(ds: DataSnapshot): StaticType.UserInfo {
        return StaticType.UserInfo(ds.child("lastConnection").value as Long,
                ds.child("pseudo").value as String)
    }

    fun GalaxyInfo() {
        mDataBaseReference.child("galaxy").addListenerForSingleValueEvent(object :
                ValueEventListener {
            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")
            }

            override fun onDataChange(ds: DataSnapshot) {
                val superMap = ds.children.flatMap {
                    val galaxy = it.key.toInt()
                    it.children.map {
                        val pos = it.key.toInt()
                        val player = it.child("pseudo").value as String
                        val namePlanet = it.child("name").value as String
                        Pair(Pair(galaxy, pos), Pair(player, namePlanet))
                    }

                }.toMap(HashMap())
                GameData.galaxyMap = superMap
            }
        })
    }


    fun disableButton() {
        for (planet in 0..UserData.planets.size) {
            mDataBaseReference.child(
                    "users/${UserData.uid}/planets/$planet/construction/").addListenerForSingleValueEvent(object :
                    ValueEventListener {
                override fun onDataChange(p0: DataSnapshot?) {
                    p0?.children?.forEach {
                        val superEnum = when (it.key) {
                            "batiments" -> SuperEnum.BUILDING
                            "defenses" -> SuperEnum.DEFENSE
                            "ships" -> SuperEnum.SHIP
                            else -> null
                        }
                        UserData.disableButton[Pair(planet, superEnum!!)] = it.childrenCount != 0L
                    }

                }

                override fun onCancelled(p0: DatabaseError?) {
                    Log.e("FireBase", "The read failed: " + p0?.message)
                }
            })
        }

    }

}