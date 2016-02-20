package net.treelzebub.zinepress.net.sync

import android.content.Context
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.net.api.PocketApiFactory

/**
 * Created by Tre Murillo on 2/20/16
 * Copyright(c) 2016 Level, Inc.
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
                .subscribe {
                    DbArticles.apply {
                        write().insertAll(uri(), it.articles)
                    }
                }
    }
}