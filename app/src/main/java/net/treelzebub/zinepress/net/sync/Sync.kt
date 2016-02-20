package net.treelzebub.zinepress.net.sync

import android.content.Context
import android.util.Log
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.net.api.PocketApiFactory
import net.treelzebub.zinepress.net.api.model.PocketArticleResponse
import net.treelzebub.zinepress.util.extensions.TAG
import rx.Subscriber

/**
 * Created by Tre Murillo on 2/20/16
 */
object Sync {

    fun requestSync(c: Context) {
        val tokenMgr = PocketTokenManager.from(c)
        val api = PocketApiFactory.newApiService()
        tokenMgr.getValidAccessToken()
                .switchMap {
                    val authBody = PocketApiFactory.articlesRequestBody(it)
                    api.getArticles(authBody)
                }
                .subscribe(object : Subscriber<PocketArticleResponse>() {
                    override fun onCompleted() {
                    }

                    override fun onNext(r: PocketArticleResponse) {
                        DbArticles.apply {
                            write().insertAll(uri(), r.articles.map { it.value })
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.message)
                    }
                })
    }
}