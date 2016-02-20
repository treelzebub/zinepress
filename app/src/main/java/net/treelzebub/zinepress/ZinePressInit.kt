package net.treelzebub.zinepress

import android.content.Context
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.auth.model.AuthedRequestBody
import rx.schedulers.Schedulers

/**
 * Created by Tre Murillo on 1/30/16
 */
object ZinePressInit {

    fun init(c: Context) {
        PocketTokenManager.from(c).storage.storedAccessToken
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .doOnNext {
                    syncArticles(it.accessToken)
                }
    }

    private fun syncArticles(token: String) {
        PocketApiFactory.newApiService()
                .getArticles(AuthedRequestBody(Constants.CONSUMER_KEY, token))
    }
}
