package net.treelzebub.zinepress.net.sync

import android.content.Context
import android.util.Log
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.net.api.Pocket
import net.treelzebub.zinepress.net.api.PocketApiFactory
import net.treelzebub.zinepress.util.extensions.TAG

/**
 * Created by Tre Murillo on 2/20/16
 */
object Sync {

    fun requestSync(c: Context) {
        Log.d(TAG, "sync requested.")
        val tokenMgr = PocketTokenManager.from(c)
        val api = PocketApiFactory.newApiService()
        tokenMgr.getValidAccessToken()
                .switchMap {
                    val authBody = Pocket.articlesRequestBody(it)
                    api.getArticles(authBody)
                }
                .doOnError {
                    Log.e(TAG, it.message)
                }
                .subscribe {
                    DbArticles.apply {
                        write().insertAll(uri(), it.articles.map { it.value })
                    }
                    Log.d(TAG, "sync complete.")
                }
    }
}