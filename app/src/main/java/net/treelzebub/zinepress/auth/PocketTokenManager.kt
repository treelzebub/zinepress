package net.treelzebub.zinepress.auth

import android.content.Context
import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.OAuth2AccessTokenManager
import de.rheinfabrik.heimdall.OAuth2AccessTokenStorage
import de.rheinfabrik.heimdall.extras.SharedPreferencesOAuth2AccessTokenStorage
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.util.PrefsUtils
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
class PocketTokenManager(storage: OAuth2AccessTokenStorage<OAuth2AccessToken>) : OAuth2AccessTokenManager<OAuth2AccessToken>(storage) {

    companion object {
        fun from(c: Context): PocketTokenManager {
            val prefs = PrefsUtils.getPrefs(c)
            val storage = SharedPreferencesOAuth2AccessTokenStorage<OAuth2AccessToken>(prefs, OAuth2AccessToken::class.java)
            return PocketTokenManager(storage)
        }
    }

    val validAccessToken: Observable<String> get() {
        val grant = PocketRefreshAccessTokenGrant(Constants.CONSUMER_KEY, Constants.REDIRECT_URI)
        return super.getValidAccessToken(grant).map { it.tokenType + " " + it.accessToken }
    }

    fun newAuthCodeGrant(): PocketAuthCodeGrant {
        val grant = PocketAuthCodeGrant()
        grant.clientId = Constants.CONSUMER_KEY
        grant.redirectUri = Constants.REDIRECT_URI
        return grant
    }
}
