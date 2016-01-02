package net.treelzebub.zinepress.auth

import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.util.PrefsUtils
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 1/2/16
 */
object LocalCredStore {

    private const val OAUTH_TOKEN  = "oauth_token"
    private const val OAUTH_SECRET = "oauth_secret"

    private val context = BaseInjection.context

    private val tokenPref  = PrefsUtils.userPref(OAUTH_TOKEN,  String::class.java)
    private val secretPref = PrefsUtils.userPref(OAUTH_SECRET, String::class.java)

    private var token: Token? = null

    fun setToken(token: Token) {
        this.token = token
        tokenPref.put(context, token.token, true)
        secretPref.put(context, token.secret, true)
    }

    fun getToken(): Token? {
        if (token == null) {
            val tokenStr  = tokenPref.get(context)  ?: return null
            val secretStr = secretPref.get(context) ?: return null
            token = Token(tokenStr, secretStr)
        }
        return token
    }

    fun clear() {
        arrayOf(tokenPref, secretPref).forEach {
            it.remove(context, true)
        }
    }
}
