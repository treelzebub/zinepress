package net.treelzebub.zinepress.auth

import android.content.Context
import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.OAuth2AccessTokenManager
import de.rheinfabrik.heimdall.OAuth2AccessTokenStorage
import de.rheinfabrik.heimdall.extras.SharedPreferencesOAuth2AccessTokenStorage
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.model.RequestToken
import net.treelzebub.zinepress.auth.model.RequestTokenBody
import net.treelzebub.zinepress.util.PrefsUtils
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
class PocketTokenManager(val c: Context, storage: OAuth2AccessTokenStorage<OAuth2AccessToken>) : OAuth2AccessTokenManager<OAuth2AccessToken>(storage) {

    companion object {
        fun from(c: Context): PocketTokenManager {
            val prefs   = PrefsUtils.getPrefs(c)
            val storage = SharedPreferencesOAuth2AccessTokenStorage<OAuth2AccessToken>(prefs, OAuth2AccessToken::class.java)
            return PocketTokenManager(c, storage)
        }
    }

    // Auth flow
    fun requestToken(): Observable<RequestToken> {
        return PocketApiFactory.newApiService().requestToken(RequestTokenBody(Constants.CONSUMER_KEY, Constants.REDIRECT_URI))
    }

    fun authUrl(code: String): String {
        return "${Constants.AUTHORIZE_URL}?request_token=$code&redirect_uri=${Constants.REDIRECT_URI}"
    }

    fun accessToken(code: String): Observable<OAuth2AccessToken> {
        val grant = PocketAuthCodeGrant()
        grant.redirectUri = Constants.REDIRECT_URI
        return grant.exchangeTokenUsingCode(code)
//        return PocketApiFactory.newApiService().newAccessToken(AccessTokenRequestBody(code, Constants.CONSUMER_KEY, Constants.REDIRECT_URI))
    }

    val validAccessToken: Observable<String> get() {
        val grant = PocketRefreshAccessTokenGrant(Constants.CONSUMER_KEY, Constants.REDIRECT_URI)
        return super.getValidAccessToken(grant).map { it.tokenType + " " + it.accessToken }
    }

    // Persist tokens
    fun saveRequestToken(code: String) {
        val pref = PrefsUtils.userPref(c.getString(R.string.pref_request_token), String::class.java)
        pref.put(c, code, true)
    }

    fun loadRequestToken(): String {
        val pref = PrefsUtils.userPref(c.getString(R.string.pref_request_token), String::class.java)
        return pref.get(c)!!
    }

    fun saveAccessToken(code: String) {
        val pref = PrefsUtils.userPref(c.getString(R.string.pref_access_token), String::class.java)
        pref.put(c, code, true)
    }

    fun loadAccessToken(): String {
        val pref = PrefsUtils.userPref(c.getString(R.string.pref_access_token), String::class.java)
        return pref.get(c)!!
    }
}
