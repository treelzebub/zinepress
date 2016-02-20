package net.treelzebub.zinepress.db.zines

import android.content.ContentValues
import android.net.Uri
import net.treelzebub.zinepress.db.IWriter
import net.treelzebub.zinepress.db.IDatabase
import net.treelzebub.zinepress.util.extensions.maybePut

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineWriter(override val parent: IDatabase<IZine>) : IWriter<IZine> {

    private val context = parent.context

    override fun bulkInsert(uri: Uri, vararg items: IZine): Boolean {
        val cvs = items.map { toContentValues(it) }
        return context.contentResolver.bulkInsert(uri, cvs.toTypedArray()) == items.size
    }

    override fun addOrUpdate(vararg items: IZine): Boolean {
        if (items.isEmpty()) return false
        return bulkInsert(parent.uri(), *items)
    }

    override fun toContentValues(item: IZine): ContentValues {
        val retval = ContentValues()
        retval.maybePut(ZineCols.ID,    item.id)
        retval.maybePut(ZineCols.DATE,  item.date)
        retval.maybePut(ZineCols.TITLE, item.title)
        retval.maybePut(ZineCols.ZINE,  item.articles)
        return retval
    }
}
