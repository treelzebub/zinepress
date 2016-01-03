package net.treelzebub.zinepress.ui.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseRxActivity() {
    private val webView: WebView by bindView(R.id.web_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val wvSettings = webView.settings
        wvSettings.builtInZoomControls = true
        wvSettings.javaScriptEnabled = true
        webView.setWebViewClient(RequestTokenCallback())
    }

    private inner class RequestTokenCallback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (!url.isNullOrBlank() && url.startsWith(Constants.REDIRECT_URI)) {
//                AuthUtils.requestAccessToken(this@LoginActivity, Uri.parse(url))
                return true
            }
            return false
        }
    }
}
