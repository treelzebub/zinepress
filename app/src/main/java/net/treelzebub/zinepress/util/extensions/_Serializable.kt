package net.treelzebub.zinepress.util.extensions

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Created by Tre Murillo on 2/20/16
 */

fun Any.bytes(): ByteArray {
    val bos = ByteArrayOutputStream()
    val oos = ObjectOutputStream(bos)
    oos.writeObject(this)
    return bos.toByteArray()
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> ByteArray.impl(): T {
    val byteStream = ByteArrayInputStream(this)
    val objStream = ObjectInputStream(byteStream)
    return objStream.readObject() as T
}
