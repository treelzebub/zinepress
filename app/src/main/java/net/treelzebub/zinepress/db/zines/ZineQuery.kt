package net.treelzebub.zinepress.db.zines

import android.database.Cursor
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.db.ZineContentProvider
import net.treelzebub.zinepress.util.listAndClose

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineQuery(override val parent: DbZines) : IQuery<IZine> {

    override fun list(): List<DbZine> {
        return cursor().listAndClose { DbZine(it) }
    }

    override fun cursor(): Cursor {
        return parent.context.contentResolver
                .query(ZineContentProvider.uri(),
                        null, null, null,
                        "${ZineCols.DATE} DESC")
    }

    override fun selection(): Pair<String, Array<String>> {
        throw UnsupportedOperationException()
    }
}
