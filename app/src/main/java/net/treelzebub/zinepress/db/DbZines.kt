package net.treelzebub.zinepress.db

import android.content.Context
import android.database.Cursor
import android.support.v4.database.DatabaseUtilsCompat
import net.treelzebub.zinepress.util.listAndClose
import net.treelzebub.zinepress.util.pairOf
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by Tre Murillo on 1/8/16
 */
class DbZines(override val context: Context) : Zines {

    private val listeners = ConcurrentLinkedQueue<ZineListener>()

    override fun all(): List<IZine> {
        return query().getList()
    }

    override fun cursor(query: ZineQuery): Cursor {
        val selection = selection(query)
        return context.contentResolver.query(
                ZineContentProvider.uri(context),
                null,
                selection.first, selection.second,
                "${ZineCols.DATE} DESC",
                null)
    }

    override fun list(query: ZineQuery): List<IZine> {
        return cursor(query).listAndClose { DbZine(it) }
    }

    override fun query(): ZineQuery = ZineQuery(this)

    override fun write(): ZineWriter = ZineWriter(this)

    override fun listen(listener: ZineListener) {
        listeners.add(listener)
    }

    override fun unlisten(listener: ZineListener) {
        listeners.remove(listener)
    }

    private fun selection(query: ZineQuery): Pair<String, Array<String>> {
        val pairs = arrayListOf<Pair<String, String?>>()
        if (query.query != null) {
            pairs.add(pairOf("${ZineCols.TITLE} LIKE ?", "%${query.query}%"))
        }
        val args = pairs.mapNotNull { it.second }.toTypedArray()
        val selection = pairs.fold("") { s, pair -> DatabaseUtilsCompat.concatenateWhere(s, pair.first) }
        return Pair(selection, args)
    }
}
