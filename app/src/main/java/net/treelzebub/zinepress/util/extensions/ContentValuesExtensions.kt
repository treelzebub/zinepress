package net.treelzebub.zinepress.util.extensions

import android.content.ContentValues

/**
 * Created by Tre Murillo on 1/8/16
 */
fun ContentValues.maybePut(key: String, value: String?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Byte?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Short?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Int?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Long?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Float?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Double?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: Boolean?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}

fun ContentValues.maybePut(key: String, value: ByteArray?, insertOnNull: Boolean = true) {
    put(key, value ?: (if (insertOnNull) null else return))
}