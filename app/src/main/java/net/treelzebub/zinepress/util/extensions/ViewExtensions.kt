package net.treelzebub.zinepress.util.extensions

import android.view.LayoutInflater
import android.view.View

/**
 * Created by Tre Murillo on 1/2/16
 */
val View.inflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun View.setVisible() { visibility = View.VISIBLE }

fun View.setInvisible() { visibility = View.INVISIBLE }

fun View.setGone() { visibility = View.GONE }

fun View.setVisibleGone(pred: Boolean) {
    if (pred) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

fun View.setVisibleInvisible(pred: Boolean) {
    if (pred) {
        visibility = View.VISIBLE
    } else {
        visibility = View.INVISIBLE
    }
}