package net.treelzebub.zinepress.db

import android.database.Cursor
import net.treelzebub.zinepress.util.listAndClose

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineQuery(val parent: Zines) {

    fun list(): List<DbZine> {
        return cursor().listAndClose { DbZine(it) }
    }

    fun cursor(): Cursor {
        return parent.context.contentResolver
                .query(ZineContentProvider.uri(),
                        null, null, null,
                        "${ZineCols._ID} DESC")
    }
}
