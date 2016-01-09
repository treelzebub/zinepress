package net.treelzebub.zinepress.util.extensions

import java.io.*

/**
 * Created by Tre Murillo on 1/8/16
 */
fun <T : Serializable> T.serialize(): ByteArray {
    val bos = ByteArrayOutputStream()
    var out: ObjectOutputStream? = null
    try {
        out = ObjectOutputStream(bos)
        out.writeObject(this)
    } finally {
        try {
            out?.close()
            bos.close()
        } catch (_: IOException) {
        }
        return bos.toByteArray()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> ByteArray.deserialize(): T {
    val bis = ByteArrayInputStream(this)
    var input: ObjectInput? = null
    try {
        input = ObjectInputStream(bis)
    } finally {
        bis.close()
        input?.close()
        return input!!.readObject() as T
    }
}