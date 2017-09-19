package com.omise.mobile.omisetumboon.ui.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.View


/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class ScrollChildSwipeRefreshLayout : SwipeRefreshLayout {

    private var mScrollUpChild: View? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun canChildScrollUp(): Boolean {
        return if (mScrollUpChild != null) {
            mScrollUpChild!!.canScrollVertically(-1)
        } else super.canChildScrollUp()
    }

    fun setScrollUpChild(view: View) {
        mScrollUpChild = view
    }
}