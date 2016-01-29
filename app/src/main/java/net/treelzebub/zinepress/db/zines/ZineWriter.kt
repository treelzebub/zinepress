package net.treelzebub.zinepress.db.zines

import android.content.ContentValues
import android.net.Uri
import net.treelzebub.zinepress.db.IWriter
import net.treelzebub.zinepress.db.ZinepressDatabase
import net.treelzebub.zinepress.util.extensions.maybePut

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineWriter(override val parent: ZinepressDatabase<IZine>) : IWriter<IZine> {

    private val context = parent.context

    override fun bulkInsert(uri: Uri, vararg items: IZine): Boolean {
        val cvs = items.map { toContentValues(it) }
        return context.contentResolver.bulkInsert(uri, cvs.toTypedArray()) == items.size
    }

    override fun addOrUpdate(vararg items: IZine): Boolean {
        if (items.isEmpty()) return false
        return bulkInsert(DbZines.uri(context), *items)
    }

    override fun toContentValues(item: IZine): ContentValues {
        val retval = ContentValues()
        retval.maybePut(ZineCols.DATE,  item.sort)
        retval.maybePut(ZineCols.TITLE, item.title)
        retval.maybePut(ZineCols.ZINE,  item.zines)
        return retval
    }
}
