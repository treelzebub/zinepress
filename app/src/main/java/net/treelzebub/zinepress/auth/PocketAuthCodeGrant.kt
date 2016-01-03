package net.treelzebub.zinepress.auth

import android.net.Uri
import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.grants.OAuth2AuthorizationCodeGrant
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.model.AccessTokenRequestBody
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
class PocketAuthCodeGrant : OAuth2AuthorizationCodeGrant<OAuth2AccessToken>() {

    override fun buildAuthorizationUri(): Uri {
        return Uri.parse(Constants.AUTHORIZE_URL)
                  .buildUpon()
                  .appendQueryParameter("client_id", clientId)
                  .appendQueryParameter("redirect_uri", redirectUri)
                  .appendQueryParameter("response_type", RESPONSE_TYPE)
                  .build()
    }

    override fun exchangeTokenUsingCode(code: String): Observable<OAuth2AccessToken> {
        val body = AccessTokenRequestBody(code, clientId, redirectUri, GRANT_TYPE)
        return PocketApiFactory.newApiService().grantNewAccessToken(body)
    }
}
