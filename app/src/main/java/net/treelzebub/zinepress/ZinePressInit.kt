package net.treelzebub.zinepress

import android.content.Context
import android.os.Handler
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.auth.model.AuthedRequestBody
import net.treelzebub.zinepress.db.articles.DbArticles
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Tre Murillo on 1/30/16
 */
object ZinePressInit {

    fun init(c: Context) {
        PocketTokenManager.from(c).storage.apply {
            storedAccessToken
                    .observeOn(AndroidSchedulers.handlerThread(Handler()))
                    .subscribe {
                        val articles = PocketApiFactory.newApiService()
                                .getArticles(AuthedRequestBody(Constants.CONSUMER_KEY, it.accessToken))
                        articles.observeOn(AndroidSchedulers.handlerThread(Handler()))
                                .subscribe {
                                    DbArticles.get(c)
                                            .write()
                                            .bulkInsert(DbArticles.uri(c), *it.articles.toTypedArray())
                                }
                    }
        }
    }
}