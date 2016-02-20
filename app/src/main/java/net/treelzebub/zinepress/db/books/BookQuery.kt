package net.treelzebub.zinepress.db.books

import android.database.Cursor
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.util.listAndClose
import nl.siegmann.epublib.domain.Book

/**
 * Created by Tre Murillo on 2/20/16
 */
class BookQuery(override val parent: DbBooks) : IQuery<Book> {

    override fun list(): List<Book> {
        return cursor().listAndClose { DbBook(it).i() }
    }

    override fun cursor(): Cursor {
        return parent.context.contentResolver
                .query(parent.uri(),
                        null, null, null,
                        "${BookCols._ID} DESC")
    }

    override fun selection(): Pair<String, Array<String>> {
        throw UnsupportedOperationException()
    }
}
