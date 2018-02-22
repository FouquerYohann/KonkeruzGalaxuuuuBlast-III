package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

import android.content.Context
import android.util.Log
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileOutputStream

object DataBaseTools {
    var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDataBaseReference: DatabaseReference = mDataBase.reference
    var mStorageInst: FirebaseStorage = FirebaseStorage.getInstance()

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    data class BuildBase(val btc: Long, val eth: Long)
    data class BuildLevel(val btc: String, val eth: String)
    data class BuildCost(val base: BuildBase, val level: BuildLevel)
    data class BuildData(val name: String, val description: String, val image: String,
                         val cost: BuildCost)


    var testing = mutableListOf<BuildData>()

    fun data(applicationContext: Context) {
        var result = mutableListOf<BuildData>()
        println("AAEZAFEEZAFRAG")
        mDataBaseReference.child("game/batiment").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.children.forEach({ dS ->
                            run {
                                val name = dS.child("name").value as String
                                val desc = dS.child("description").value as String
                                val image = dS.child("image").value as String
                                val referenceFromUrl = mStorageInst.getReference(image)
                                val ethCost = dS.child("cost/base/eth").value as Long
                                val btcCost = dS.child("cost/base/btc").value as Long
                                val ethLevel = dS.child("cost/level/eth").value as String
                                val btcLevel = dS.child("cost/level/btc").value as String
                                val element = BuildData(name, desc, image,
                                        BuildCost(BuildBase(btcCost, ethCost),
                                                BuildLevel(btcLevel, ethLevel)))
                                println("\n" + element + "\n")


                                val dir = applicationContext.getDir("imagesDir",Context.MODE_PRIVATE )
                                if (!dir.exists()) {
                                    dir.mkdir()
                                }
                                val imgFile = File(dir, image)
                                referenceFromUrl.getBytes(1024*1024).addOnSuccessListener { bytes ->
                                    FileOutputStream(imgFile).write(bytes)
                                    Log.i("DownImage", image + " finished at " + imgFile.canonicalFile)

                                }.addOnFailureListener { exception ->
                                    Log.d("Download Images", exception.message)
                                }

                                result.add(element)
                            }
                        })
                    }

                    override fun onCancelled(dError: DatabaseError?) {
                        println("CANCELLLLLLLLLLLLLLLLED")
                        println("loadPost:onCancelled ${dError?.toException()}")
                    }

                })
        println("LE RESULTAT " + result)
        testing= result
    }


}