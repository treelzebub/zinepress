package net.treelzebub.zinepress.util

import android.content.Context
import android.net.Uri
import net.treelzebub.zinepress.async.async
import net.treelzebub.zinepress.auth.AuthService
import net.treelzebub.zinepress.auth.LocalCredStore
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 1/2/16
 */
object AuthUtils {

    /**
     * Using the request token we retrieved earlier, get that sweet, sweet Access Token that will
     * allow access to Discogs' protected resources.
     *
     * @param c: a Context used to access uMAP's SharedPreferences and String Resources.
     * @param requestToken: the request token received from the auth url
     * @param data: the URI we caught from Discog's callback, after the user authorized the app.
     * */
    fun requestAccessToken(c: Context, data: Uri) {
        async() {
            val sAuth = AuthService.service
            val requestToken = sAuth.requestToken
            val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
            val accessToken = sAuth.getAccessToken(requestToken, verifier)
            if (accessToken != null) {
                LocalCredStore.setToken(accessToken)
                //                c.startActivity(DashboardActivity.getIntent(c))
            } else {
                // TODO
            }
        }
    }
}
