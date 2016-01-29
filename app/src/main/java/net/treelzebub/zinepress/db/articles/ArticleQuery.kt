package net.treelzebub.zinepress.db.articles

import android.database.Cursor
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.util.listAndClose
import net.treelzebub.zinepress.util.pairOf

/**
 * Created by Tre Murillo on 1/28/16
 */
class ArticleQuery(override val parent: DbArticles) : IQuery<IArticle> {

    private var title: String? = null
        private set
    private var url: String? = null
        private set

    override fun list(): List<IArticle> {
        return parent.cursor(this).listAndClose { DbArticle(it) }
    }

    override fun cursor(): Cursor {
        val selection = selection()
        return parent.context.contentResolver.query(
                DbArticles.uri(parent.context),
                null,
                selection.first, selection.second,
                "")
    }

    override fun selection(): Pair<String, Array<String>> {
        val pairs = arrayListOf<Pair<String, String?>>()
        if (title != null) {
            pairs.add(pairOf(ArticleCols.TITLE, title))
        }
        if (url != null) {
            pairs.add(pairOf(ArticleCols.URL, url))
        }
        return formatSelection(*pairs.toTypedArray())
    }
}
