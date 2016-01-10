package net.treelzebub.zinepress.util

import android.database.Cursor

/**
 * Created by Tre Murillo on 1/8/16
 */
fun <T1, T2> pairOf(t1: T1, t2: T2): Pair<T1, T2> {
    return Pair(t1, t2)
}

fun <T> Cursor.listAndClose(transform: (Cursor) -> T): List<T> {
    val retval = list(transform)
    close()
    return retval
}

inline fun <T> Cursor.list(transform: (Cursor) -> T): List<T> {
    val position = position
    val retval = arrayListOf<T>()
    while (moveToNext()) {
        retval.add(transform(this))
    }
    moveToPosition(position)
    return retval
}