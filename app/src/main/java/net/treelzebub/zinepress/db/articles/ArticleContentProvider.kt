package net.treelzebub.zinepress.db.articles

import net.treelzebub.zinepress.db.BaseContentProvider

/**
 * Created by Tre Murillo on 1/31/16
 */
class ArticleContentProvider : BaseContentProvider(ArticleCols._TABLE) {

    override fun onCreate(): Boolean {
        helper = ArticlesSQLiteHelper(context)
        return super.onCreate()
    }
}
