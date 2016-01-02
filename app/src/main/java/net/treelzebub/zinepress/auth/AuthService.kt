package net.treelzebub.zinepress.auth

import net.treelzebub.zinepress.Constants
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token
import org.scribe.oauth.OAuthService

/**
 * Created by Tre Murillo on 1/2/16
 */
object AuthService {

    val service: OAuthService get() =
        ServiceBuilder()
                .provider(ReadabilityApi::class.java)
                .apiKey(Constants.CONSUMER_KEY)
                .apiSecret(Constants.CONSUMER_SECRET)
                .callback(Constants.CALLBACK_URL)
                .build()

    class ReadabilityApi : DefaultApi10a() {
        override fun getAccessTokenEndpoint(): String {
            return Constants.ACCESS_TOKEN_URL
        }

        override fun getRequestTokenEndpoint(): String {
            return Constants.REQUEST_TOKEN_URL
        }

        override fun getAuthorizationUrl(requestToken: Token): String {
            return Constants.AUTHORIZE_URL
        }
    }
}
