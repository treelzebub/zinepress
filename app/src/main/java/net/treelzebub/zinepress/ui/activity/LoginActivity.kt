package net.treelzebub.zinepress.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketAuthCodeGrant
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.setGone
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_login.web_view as webView
import kotlinx.android.synthetic.main.activity_login.temp_text as tempText

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tempText.setGone()
        setupWebView()
        requestToken()
    }

    private fun setupWebView() {
        val wvSettings = webView.settings
        wvSettings.builtInZoomControls = true
        wvSettings.javaScriptEnabled   = true
        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Hide redirect page from user
                if (url.startsWith(Constants.REDIRECT_URI)) {
                    val manager = PocketTokenManager.from(this@LoginActivity)
                    accessToken(manager.loadRequestToken())
                }
            }
        })
    }

    private fun requestToken() {
        val manager = PocketTokenManager.from(this)
        val grant = PocketAuthCodeGrant()
        grant.clientId    = Constants.CONSUMER_KEY
        grant.redirectUri = Constants.REDIRECT_URI
        LifecycleObservable.bindActivityLifecycle(lifecycle(), manager.requestToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                manager.saveRequestToken(it.code)
                webView.loadUrl(manager.authUrl(it.code))
            }
    }

    private fun accessToken(code: String) {
        val manager = PocketTokenManager.from(this)
        LifecycleObservable.bindActivityLifecycle(lifecycle(), manager.accessToken(code))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    manager.storage.storeAccessToken(it)
                }
    }
}
