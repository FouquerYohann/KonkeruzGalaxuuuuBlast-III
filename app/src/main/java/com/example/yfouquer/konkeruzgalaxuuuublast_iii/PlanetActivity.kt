package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.graphics.Bitmap

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.widget.Toast
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R.drawable.planet_0
import kotlinx.android.synthetic.main.planet_activity.*
import kotlinx.android.synthetic.main.view_row.*
/**
 * Created by yfouquer on 12/03/18.
 */
class PlanetActivity : AppCompatActivity(){

    lateinit var swipeDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planet_activity)

        var planet = intent.getIntExtra("planet", 0)

//        imageView.setImageDrawable(getDrawable(R.drawable.planet_0))

        title_for_constraint.text = UserData.planets[planet].name

        btc.text = UserData.planets[planet].ressource.btc.toString()
        eth.text = UserData.planets[planet].ressource.eth.toString()

        swipeDetector = GestureDetectorCompat(this, OnSwipeListener(applicationContext))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        swipeDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

}
