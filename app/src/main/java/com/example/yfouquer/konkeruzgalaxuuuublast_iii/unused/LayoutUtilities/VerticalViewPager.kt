package com.example.yfouquer.konkeruzgalaxuuuublast_iii.unused.LayoutUtilities

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by yfouquer on 13/03/18.
 */
class VerticalViewPager : ViewPager {

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun swapXY(e: MotionEvent): MotionEvent {
        e.setLocation((e.y / height) * width, (e.x / width) * height)
        return e
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val tev = super.onInterceptTouchEvent(ev)
        swapXY(ev!!)
        return tev
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(swapXY(ev!!))
    }

    private class VerticalPageTransformer : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            when {
                position < -1 -> page.alpha = 0.0F
                position <= 1 -> {
                    page.alpha = 1.0F
                    page.translationX = page.width * -position
                    page.translationY = position * page.height
                }
                else -> page.alpha = 0.0F
            }

        }

    }
}