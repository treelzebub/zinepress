package net.treelzebub.zinepress.db.zines

import android.database.Cursor
import net.treelzebub.zinepress.db.ZineContentProvider
import net.treelzebub.zinepress.util.listAndClose

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineQuery(val parent: Zines) {

    var query: String? = null
        private set

    fun contains(query: String?): ZineQuery = apply {
        this.query = query
    }

    fun list(): List<DbZine> {
        return cursor().listAndClose { DbZine(it) }
    }

    fun cursor(): Cursor {
        return parent.context.contentResolver
                .query(ZineContentProvider.uri(),
                        null, null, null,
                        "${ZineCols._ID} DESC")
    }

    fun getList(): List<IZine> {
        return parent.list(this)
    }
}
