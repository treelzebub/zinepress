package net.treelzebub.zinepress.db.zines

import android.database.Cursor

/**
 * Created by Tre Murillo on 1/8/16
 */
class DbZine : IZine {

    var i = 0 // 0 is _ID
    override val id: String
    override val sort: Long
    override val title: String
    override val zines: ByteArray

    constructor(c: Cursor) {
        id    = c.getString(++i)
        sort  = c.getLong(++i)
        title = c.getString(++i)
        zines = c.getBlob(++i)
    }
}
