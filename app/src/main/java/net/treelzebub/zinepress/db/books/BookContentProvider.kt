package net.treelzebub.zinepress.db.books

import net.treelzebub.zinepress.db.BaseContentProvider
import net.treelzebub.zinepress.util.BaseInjection

/**
 * Created by Tre Murillo on 2/20/16
 */
class BookContentProvider : BaseContentProvider(BookCols._TABLE) {

    override fun onCreate(): Boolean {
        helper = BookSQLiteHelper(context)
        return super.onCreate()
    }
}
