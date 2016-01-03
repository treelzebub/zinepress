package net.treelzebub.zinepress.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketAuthCodeGrant
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.auth.model.RequestToken
import net.treelzebub.zinepress.util.ToastUtils
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseRxActivity() {
    private val webView: WebView by bindView(R.id.web_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
                    webView.visibility = View.GONE
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
                Log.d("Request Token", it.code)
                webView.loadUrl(manager.authUrl(it.code))
            }
    }

    private fun accessToken(requestToken: RequestToken) {
        val manager = PocketTokenManager.from(this)
        LifecycleObservable.bindActivityLifecycle(lifecycle(), manager.accessToken(requestToken))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }
    }
}
