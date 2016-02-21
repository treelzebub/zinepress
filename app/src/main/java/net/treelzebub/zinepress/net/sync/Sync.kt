package net.treelzebub.zinepress.net.sync

import android.content.Context
import android.content.Intent
import android.util.Log
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.net.api.Pocket
import net.treelzebub.zinepress.net.api.PocketApiFactory
import net.treelzebub.zinepress.net.api.model.PocketArticleResponse
import net.treelzebub.zinepress.ui.activity.LoginActivity
import net.treelzebub.zinepress.util.extensions.TAG
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Tre Murillo on 2/20/16
 */
object Sync {

    fun requestSync(c: Context): Observable<PocketArticleResponse> {
        Log.d(TAG, "sync requested.")
        val tokenMgr = PocketTokenManager.from(c)
        val api = PocketApiFactory.newApiService()
        return tokenMgr.getValidAccessToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e(TAG, it.message)
                }
                .switchMap {
                    val authBody = Pocket.articlesRequestBody(it)
                    api.getArticles(authBody)
                }
                .doOnNext {
                    DbArticles.apply {
                        write().insertAll(uri(), it.articles.map { it.value })
                    }
                    Log.d(TAG, "sync complete.")
                }
    }
}