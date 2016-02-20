package net.treelzebub.zinepress.db.articles

import android.content.ContentValues
import android.net.Uri
import net.treelzebub.zinepress.db.IWriter
import net.treelzebub.zinepress.net.api.model.PocketArticle
import net.treelzebub.zinepress.util.extensions.maybePut

/**
 * Created by Tre Murillo on 1/28/16
 */
class ArticleWriter(override val parent: DbArticles) : IWriter<IArticle> {

    private val context = parent.context

    fun insertAll(uri: Uri, list: List<PocketArticle>): Boolean {
        return bulkInsert(uri, *list.toTypedArray())
    }

    override fun bulkInsert(uri: Uri, vararg items: IArticle): Boolean {
        val cvs = items.map { toContentValues(it) }
        return context.contentResolver.bulkInsert(uri, cvs.toTypedArray()) == items.size
    }

    override fun addOrUpdate(vararg items: IArticle): Boolean {
        if (items.isEmpty()) return false
        return bulkInsert(parent.uri(), *items)
    }

    override fun toContentValues(item: IArticle): ContentValues {
        val retval = ContentValues()
        retval.maybePut(ArticleCols.ID,    item.id)
        retval.maybePut(ArticleCols.DATE,  item.date)
        retval.maybePut(ArticleCols.TITLE, item.title)
        retval.maybePut(ArticleCols.URL,   item.originalUrl)
        return retval
    }
}
