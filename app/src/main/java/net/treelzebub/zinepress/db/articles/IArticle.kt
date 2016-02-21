package net.treelzebub.zinepress.db.articles

import java.io.Serializable

/**
 * Created by Tre Murillo on 1/28/16
 */
interface IArticle : Serializable {


    val id: Long
    val date: Long
    val title: String
    val originalUrl: String

    fun pocketUrl(): String {
        return "https://getpocket.com/a/read/$id"
    }
}
