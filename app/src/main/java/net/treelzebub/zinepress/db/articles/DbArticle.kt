package net.treelzebub.zinepress.db.articles

import android.database.Cursor

/**
 * Created by Tre Murillo on 1/24/16
 */
class DbArticle : IArticle {

    var i = 0 // 0 is _ID
    override val id: Long
    override val date: Long
    override val title: String
    override val originalUrl: String

    constructor(c: Cursor) {
        id          = c.getLong(++i)
        date        = c.getLong(++i)
        title       = c.getString(++i)
        originalUrl = c.getString(++i)
    }

    fun pocketUrl(): String {
        return "https://getpocket/a/read/$id"
    }
}
