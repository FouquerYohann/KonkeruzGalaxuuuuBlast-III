package com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools

/**
 * Created by arthur on 20/03/18.
 */

object calculEngine {
    fun timeBaTech(level: Int, robot: Int, base: Int): Int {
        val mulRobot = if (robot <= 0) 1.0 else (2.0 / robot)
        return (base * Math.pow(2.0, level + 1.0) * mulRobot).toInt()
    }

    fun timeShipDef(level: Int, robot: Int, spaceCenter: Int, base: Int): Int {
        val mulSpace = if (spaceCenter <= 0) 1.0 else (2.0 / spaceCenter)
        val mulRobot = if (robot <= 0) 1.0 else (2.0 / robot)
        return (base * Math.pow(2.0, level + 1.0) * mulRobot * mulSpace).toInt()
    }

    fun cost(level: Int, base: Int): Int {
        return (base * Math.pow(2.0, level + 1.0)).toInt()
    }

    fun mine(level: Int, tmp: Int): Int {
        return (((100 * Math.pow(1.6, level + 1.0)) / 3600) * tmp).toInt()
    }

}