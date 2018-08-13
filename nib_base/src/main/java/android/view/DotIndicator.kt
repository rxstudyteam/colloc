package android.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet

import java.util.ArrayList

class DotIndicator : View {
    private var mH: Int = 0
    private var mW: Int = 0
    private val bounds = ArrayList<Rect>()
    private var mCurrent: Int = 0
    private lateinit var mNormal: Drawable
    private lateinit var mSelected: Drawable


    private var mCount: Int = 0

    private var space = .5f

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    fun setWithPager(pager: ViewPager) {
        mCount = if (pager.adapter != null) pager.adapter!!.count else 0
        pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                mCount = if (pager.adapter != null) pager.adapter!!.count else 0
                setCurrentPosition(position)
                invalidate()
            }
        })
        updateUI()
        invalidate()
    }

    fun setCount(count: Int) {
        mCount = count
        updateUI()
        invalidate()
    }

    fun setSpace(space: Float) {
        this.space = space
        updateUI()
        invalidate()
    }

    fun setDrawable(@DrawableRes normal: Int, @DrawableRes selected: Int) {
        this.mNormal = ContextCompat.getDrawable(context, normal)!!
        this.mSelected = ContextCompat.getDrawable(context, selected)!!
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mH = h
        mW = w
        updateUI()
        invalidate()
    }

    fun updateUI() {
        val count = mCount
        if (count < 2)
            return

        val size = mH
        val space = mH * this.space

        var s = mW / 2f - (count * size + (count - 1f) * space) / 2f

        bounds.clear()
        for (i in 0 until count) {
            bounds.add(Rect(s.toInt(), 0, s.toInt() + size, size))
            s += space + size
        }
        //		Log.l(bounds.size());
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in bounds.indices) {
            var drawable = if (i == mCurrent) mSelected else mNormal
            drawable.setBounds(bounds[i])
            drawable.draw(canvas)
        }
    }

    fun setCurrentPosition(position: Int) {
        mCurrent = position
        invalidate()
    }
}
