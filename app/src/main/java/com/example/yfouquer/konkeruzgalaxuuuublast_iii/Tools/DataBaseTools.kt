package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

import android.content.Context
import android.util.Log
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.GameData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.BuildData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.Cost
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.CostBase
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.StaticType.CostLevel
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

object DataBaseTools {
    var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDataBaseReference: DatabaseReference = mDataBase.reference
    var mStorageInst: FirebaseStorage = FirebaseStorage.getInstance()

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun nameDescImage(ds: DataSnapshot): Triple<String, String, String> {
        return Triple(ds.child("name").value as String, ds.child("description").value as String,
                ds.child("image").value as String)
    }

    fun makeCostBase(ds: DataSnapshot): CostBase {
        return CostBase(ds.child("btc").value as Long, ds.child("eth").value as Long)
    }

    fun makeCostLevel(ds: DataSnapshot): CostLevel {
        return CostLevel(ds.child("btc").value as String, ds.child("eth").value as String)
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
                        val cB = makeCostBase(ds.child("cost/base"))
                        val cL = makeCostLevel(ds.child("cost/level"))

                        dowloadImage(applicationContext, image, referenceFromUrl)
                        GameData.add(BuildData(name, desc, image, Cost(cB, cL)))
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
                    val cB = makeCostBase(ds.child("cost"))
                    val defStats = makeDefStat(ds.child("stats"))
                    val techs = ds.child("techs").children.map {
                        Pair(Integer.parseInt(it.key), it.value as Long)
                    }.toCollection(mutableListOf())
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(DefData(name, desc, image, defStats, Cost(cB,null), techs))
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
                    val cB = makeCostBase(ds.child("cost/base"))
                    val cL = makeCostLevel(ds.child("cost/level"))
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(TechData(name, desc, image, Cost(cB, cL)))
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
                    val cB = makeCostBase(ds.child("cost"))
                    val stats = makeShipStat(ds.child("stats"))
                    val techs = ds.child("techs").children.map {
                        Pair(Integer.parseInt(it.key), it.value as Long)
                    }.toCollection(mutableListOf())
                    dowloadImage(applicationContext, image, imageRef)
                    GameData.add(ShipData(name, desc, image, stats, Cost(cB,null), techs))
                }
            }

            override fun onCancelled(dError: DatabaseError?) {
                println("loadPost:onCancelled ${dError?.toException()}")

            }
        })
    }

}