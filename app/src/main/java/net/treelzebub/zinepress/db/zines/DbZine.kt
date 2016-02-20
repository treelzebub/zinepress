package net.treelzebub.zinepress.db.zines

import android.database.Cursor

/**
 * Created by Tre Murillo on 1/8/16
 */
class DbZine : IZine {

    var i = 0 // 0 is _ID
    override val id: Long
    override val date: Long
    override val title: String
    override val articles: ByteArray

    constructor(c: Cursor) {
        id       = c.getLong(++i)
        date     = c.getLong(++i)
        title    = c.getString(++i)
        articles = c.getBlob(++i)
    }
}
