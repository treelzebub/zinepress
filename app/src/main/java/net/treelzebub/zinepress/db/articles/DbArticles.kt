package net.treelzebub.zinepress.db.articles

import android.content.Context
import android.database.Cursor
import android.net.Uri
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.db.IQuery
import net.treelzebub.zinepress.db.ZinepressDatabase
import net.treelzebub.zinepress.db.zines.DbZines
import net.treelzebub.zinepress.util.BaseInjection

/**
 * Created by Tre Murillo on 1/28/16
 */
class DbArticles(override val context: Context) : ZinepressDatabase<IArticle> {

    companion object {
        private var singleton: DbZines? = null

        @Synchronized
        fun get(c: Context = BaseInjection.context): DbZines {
            if (singleton == null) {
                singleton = DbZines(c)
            }
            return singleton!!
        }

        fun uri(c: Context = BaseInjection.context): Uri {
            return Uri.Builder()
                    .scheme("content")
                    .authority(c.getString(R.id.authority_articles))
                    .build()
        }
    }

    override fun write(): ArticleWriter = ArticleWriter(this)

    override fun all(): List<IArticle> = query().list()

    override fun query(): ArticleQuery = ArticleQuery(this)

    override fun cursor(query: IQuery<IArticle>): Cursor = query.cursor()

    override fun list(query: IQuery<IArticle>): List<IArticle> = query.list()
}
