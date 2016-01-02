package net.treelzebub.zinepress.ui.activity

import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.async.async
import net.treelzebub.zinepress.auth.AuthService
import net.treelzebub.zinepress.util.AuthUtils

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseAuthActivity() {
    private val webView: WebView by bindView(R.id.web_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val wvSettings = webView.settings
        wvSettings.builtInZoomControls = true
        wvSettings.javaScriptEnabled = true
        webView.setWebViewClient(RequestTokenCallback())
        loadAuthUrl()
    }

    private fun loadAuthUrl() {
        var authUrl: String = ""
        async<String>({
            val sAuth = AuthService.service
            val rt = sAuth.requestToken
            sAuth.getAuthorizationUrl(rt)
        }, {
            webView.loadUrl(it)
        })
    }

    private inner class RequestTokenCallback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (!url.isNullOrBlank() && url.startsWith(Constants.CALLBACK_URL)) {
                AuthUtils.requestAccessToken(this@LoginActivity, Uri.parse(url))
                return true
            }
            return false
        }
    }
}
