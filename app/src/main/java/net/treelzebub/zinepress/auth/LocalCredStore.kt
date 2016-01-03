package net.treelzebub.zinepress.auth

import de.rheinfabrik.heimdall.OAuth2AccessToken
import de.rheinfabrik.heimdall.OAuth2AccessTokenManager
import de.rheinfabrik.heimdall.extras.SharedPreferencesOAuth2AccessTokenStorage
import de.rheinfabrik.heimdall.grants.OAuth2Grant
import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.util.PrefsUtils

/**
 * Created by Tre Murillo on 1/2/16
 */
object LocalCredStore {

    private val prefs = PrefsUtils.getPrefs(BaseInjection.context)
    private val storage = SharedPreferencesOAuth2AccessTokenStorage<OAuth2AccessToken>(prefs, OAuth2AccessToken::class.java)
    private val manager = OAuth2AccessTokenManager<OAuth2AccessToken>(storage)


}