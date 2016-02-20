package net.treelzebub.zinepress.db.articles

import android.content.Context
import android.util.Log
import com.squareup.sqlbrite.BriteDatabase
import rx.schedulers.Schedulers
import com.squareup.sqlbrite.QueryObservable
import com.squareup.sqlbrite.SqlBrite
import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.db.articles.ArticleCols._TABLE
import net.treelzebub.zinepress.util.TAG

/**
 * Created by Tre Murillo on 1/28/16
 */
object DbArticles {

    val sqlBrite: SqlBrite
    val db: BriteDatabase

    init {
        val c: Context = BaseInjection.context
        sqlBrite = SqlBrite.create()
        db = sqlBrite.wrapDatabaseHelper(ArticlesSQLiteHelper(c), Schedulers.io())
        db.setLoggingEnabled(true)
    }

    fun write(list: List<IArticle>) {
        val rowIds = hashSetOf<Long>()
        list.forEach {
            rowIds.add(
                db.insert(_TABLE, it.toContentValues())
            )
        }
        if (rowIds.size == list.size && rowIds.none { it == -1L }) {
            Log.d(TAG, "Wrote ${rowIds.size} successfully")
        } else {
            Log.d(TAG, "Articles size was ${list.size}; wrote ${rowIds.filter { it != -1L }.size}")
        }
    }

    fun query(): QueryObservable {
        return db.createQuery(_TABLE, "SELECT * FROM $_TABLE")
    }
}
