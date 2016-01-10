package net.treelzebub.zinepress.db

/**
 * Created by Tre Murillo on 1/8/16
 */
interface IZine {
    val id: String
    val sort: Long
    val title: String
    val zines: ByteArray
}
