package net.treelzebub.zinepress.db.books

import android.database.Cursor
import net.treelzebub.zinepress.util.extensions.impl
import nl.siegmann.epublib.domain.Book

/**
 * Created by Tre Murillo on 2/20/16
 */
class DbBook {

    var i = 0 // 0 is _ID
    val book: ByteArray

    fun i(): Book {
        return book.impl<Book>()
    }

    constructor(c: Cursor) {
        this.book = c.getBlob(++i)
    }
}
