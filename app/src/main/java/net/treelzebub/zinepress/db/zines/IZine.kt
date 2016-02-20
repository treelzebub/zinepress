package net.treelzebub.zinepress.db.zines

import java.io.Serializable

/**
 * Created by Tre Murillo on 1/8/16
 */
interface IZine : Serializable {
    val id: Long
    val date: Long
    val title: String
    val articles: ByteArray
}
