package net.treelzebub.zinepress.auth

import android.net.Uri
import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.grants.OAuth2AuthorizationCodeGrant
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.auth.model.AccessTokenRequestBody
import net.treelzebub.zinepress.auth.model.PocketAccessToken
import net.treelzebub.zinepress.net.api.PocketApiFactory
import net.treelzebub.zinepress.util.UserUtils
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
class PocketAuthCodeGrant : OAuth2AuthorizationCodeGrant<PocketAccessToken>() {

    override fun buildAuthorizationUri(): Uri {
        return Uri.parse(Constants.AUTHORIZE_URL)
                .buildUpon()
                .appendQueryParameter("consumer_key", clientId)
                .appendQueryParameter("redirect_uri", redirectUri)
                .appendQueryParameter("X-Accept", "application/json")
                .build()
    }

    override fun exchangeTokenUsingCode(code: String): Observable<PocketAccessToken> {
        val body = AccessTokenRequestBody(code, Constants.CONSUMER_KEY, redirectUri)
        return PocketApiFactory.newApiService().newAccessToken(body)
    }
}
