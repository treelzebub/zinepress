package net.treelzebub.zinepress.db.books

import android.content.ContentValues
import android.net.Uri
import net.treelzebub.zinepress.db.IWriter
import net.treelzebub.zinepress.util.extensions.bytes
import net.treelzebub.zinepress.util.extensions.maybePut
import nl.siegmann.epublib.domain.Book

/**
 * Created by Tre Murillo on 2/20/16
 */
class BookWriter(override val parent: DbBooks) : IWriter<Book> {

    private val context = parent.context

    override fun bulkInsert(uri: Uri, vararg items: Book): Boolean {
        val cvs = items.map { toContentValues(it) }
        return context.contentResolver.bulkInsert(uri, cvs.toTypedArray()) == items.size
    }

    override fun addOrUpdate(vararg items: Book): Boolean {
        if (items.isEmpty()) return false
        return bulkInsert(parent.uri(), *items)
    }

    override fun toContentValues(item: Book): ContentValues {
        val retval = ContentValues()
        retval.maybePut(BookCols.BOOK, item.bytes())
        return retval
    }
}
