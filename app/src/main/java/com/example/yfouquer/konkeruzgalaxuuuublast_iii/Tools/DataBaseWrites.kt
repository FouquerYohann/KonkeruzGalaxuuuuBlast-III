package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.SuperEnum
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

/**
 * Created by yfouquer on 15/03/18.
 */
object DataBaseWrites {
    var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var databaseReference = mDataBase.reference
    var mStorageInst: FirebaseStorage = FirebaseStorage.getInstance()


    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun writesToBuilding(planet: Int, data: SuperEnum, ref: Int, nb: Int?): Unit {
        val s = when (data) {
            SuperEnum.BUILDING -> "batiments"
            SuperEnum.SHIP -> "ships"
            SuperEnum.TECH -> "techs"
            SuperEnum.DEFENSE -> "defenses"
        }

        val refBase = mDataBase.getReference("users/${UserData.uid}/planets/$planet/toBuild/$s")

        when (data) {
            SuperEnum.BUILDING -> refBase.setValue(ref)
            SuperEnum.SHIP -> refBase.child(ref.toString()).setValue(nb)
            SuperEnum.TECH -> refBase.setValue(ref)
            SuperEnum.DEFENSE -> refBase.child(ref.toString()).setValue(nb)
        }
    }
}