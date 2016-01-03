package net.treelzebub.zinepress.auth

import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.grants.OAuth2RefreshAccessTokenGrant
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.model.RefreshTokenRequestBody
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
class PocketRefreshAccessTokenGrant(val clientId: String, val redirectUri: String) :OAuth2RefreshAccessTokenGrant<OAuth2AccessToken>() {

    override fun grantNewAccessToken(): Observable<OAuth2AccessToken>? {
        val body = RefreshTokenRequestBody(refreshToken, clientId, redirectUri, GRANT_TYPE)
        return PocketApiFactory.newApiService().refreshAccessToken(body)
    }
}
