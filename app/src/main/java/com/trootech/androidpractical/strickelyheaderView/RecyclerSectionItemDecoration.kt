package com.trootech.androidpractical.strickelyheaderView

import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.trootech.androidpractical.R


class RecyclerSectionItemDecoration() : ItemDecoration() {
    private var headerOffset = 0
    private var sticky = false
    private var sectionCallback: SectionCallback? = null

    private var headerView: View? = null
    private lateinit var tvTitleHeader: TextView

    constructor(
        headerHeight: Int,
        sticky: Boolean,
        sectionCallback: SectionCallback
    ) : this() {
        headerOffset = headerHeight
        this.sticky = sticky
        this.sectionCallback = sectionCallback
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        if (sectionCallback!!.isSection(pos)) {
            outRect.top = headerOffset
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (headerView == null) {
            headerView = inflateHeaderView(parent)
            tvTitleHeader = headerView!!.findViewById<View>(R.id.tv_title_header) as TextView
            fixLayoutSize(headerView!!, parent)
        }

        var previousHeader: CharSequence? = ""
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val title = sectionCallback!!.getSectionHeader(position)
            tvTitleHeader.setText(title)
            if (previousHeader != title || sectionCallback!!.isSection(position)) {
                drawHeader(c, child, headerView!!)
                previousHeader = title
            }
        }
    }

    private fun drawHeader(
        c: Canvas,
        child: View,
        headerView: View
    ) {
        c.save()
        if (sticky) {
            c.translate(0F, Math.max(0, child.top - headerView.height).toFloat())
        } else {
            c.translate(0F, (child.top - headerView.height).toFloat())
        }
        headerView.draw(c)
        c.restore()
    }

    private fun inflateHeaderView(parent: RecyclerView): View? {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_header, parent, false)
    }

    private fun fixLayoutSize(view: View, parent: ViewGroup) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            parent.width,
            View.MeasureSpec.EXACTLY
        )
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            parent.height,
            View.MeasureSpec.UNSPECIFIED
        )
        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }
}

interface SectionCallback {
    fun isSection(position: Int): Boolean
    fun getSectionHeader(position: Int): CharSequence?
}