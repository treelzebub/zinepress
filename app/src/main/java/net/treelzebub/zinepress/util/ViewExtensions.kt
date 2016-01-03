package net.treelzebub.zinepress.util

import android.view.View

/**
 * Created by Tre Murillo on 1/2/16
 */
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