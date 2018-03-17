package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Utilities

import android.content.Context
import android.content.Intent
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Galaxy.SystemActivity

/**
 * Created by yfouquer on 12/03/18.
 */

class OnSwipeListener(val applicationContext: Context) : GestureDetector.SimpleOnGestureListener(){

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float,
                         velocityY: Float): Boolean {
        if (e1 ==null || e2 == null){
            return false
        }
        val angleRad = Math.atan2((e1.y - e2.y).toDouble(), (e2.x - e1.x).toDouble()) + Math.PI

        val direction = when(angleRad){
            in Math.PI /4 .. 3* Math.PI /4 -> "DOWN"
            in 5* Math.PI /4 .. 7* Math.PI /4 -> "UP"
            in 0.0 .. Math.PI /4 -> "LEFT"
            in 7* Math.PI /4 .. 2* Math.PI -> "LEFT"
            else -> "RIGHT"
        }

        if (direction === "LEFT"){
            applicationContext.startActivities(Array(1,{ Intent(applicationContext,SystemActivity::class.java) }))
        }

        return true
    }
}