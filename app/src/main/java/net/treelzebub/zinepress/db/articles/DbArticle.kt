package net.treelzebub.zinepress.db.articles

import android.database.Cursor
import net.treelzebub.zinepress.api.model.PocketArticle

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

    constructor(a: PocketArticle) {
        id          = a.id
        date        = a.date
        title       = a.title
        originalUrl = a.originalUrl
    }
}

fun PocketArticle.i(): IArticle {
    return DbArticle(this)
}