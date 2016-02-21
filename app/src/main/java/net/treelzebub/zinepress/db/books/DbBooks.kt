package net.treelzebub.zinepress.db.books

import android.content.Context
import android.database.Cursor
import android.net.Uri
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.db.IDatabase
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.util.BaseInjection
import nl.siegmann.epublib.domain.Book

/**
 * Created by Tre Murillo on 2/20/16
 */
object DbBooks : IDatabase<Book> {

    override val context: Context get() = BaseInjection.context

    override fun uri(): Uri {
        return Uri.Builder()
            .scheme("content")
            .authority(context.getString(R.string.authority_books))
            .build()
    }

    override fun write(): BookWriter = BookWriter(this)

    override fun all(): List<Book> = query().list()

    override fun query(): BookQuery = BookQuery(this)

    override fun cursor(query: IQuery<Book>): Cursor = query().cursor()

    override fun list(query: IQuery<Book>): List<Book> = query.list()
}
