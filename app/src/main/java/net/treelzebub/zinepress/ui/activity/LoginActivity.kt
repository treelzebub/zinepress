package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketAuthCodeGrant
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.util.ToastUtils
import net.treelzebub.zinepress.util.UserUtils
import net.treelzebub.zinepress.util.extensions.TAG
import net.treelzebub.zinepress.util.extensions.setGone
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.web_view as webView

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseRxActivity() {

    private val tokenMgr: PocketTokenManager get() = PocketTokenManager.from(this@LoginActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupWebView()
        requestToken()
    }

    private fun setupWebView() {
        webView.apply {
            settings.apply {
                builtInZoomControls = true
                javaScriptEnabled   = true
            }
            setWebViewClient(object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    if (url.startsWith(Constants.REDIRECT_URI)) {
                        setGone()
                        storeAccessToken(tokenMgr.loadRequestToken())
                    }
                }
            })
        }
    }

    private fun requestToken() {
        Log.d(TAG, "Requesting RequestToken...")
        val manager = PocketTokenManager.from(this)
        val grant = PocketAuthCodeGrant()
        grant.clientId = Constants.CONSUMER_KEY
        grant.redirectUri = Constants.REDIRECT_URI
        LifecycleObservable.bindActivityLifecycle(lifecycle(), manager.requestToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "RequestToken received with hash code ${it.hashCode()}.")
                    manager.saveRequestToken(it.code)
                    webView.loadUrl(manager.authUrl(it.code))
                }
    }

    private fun storeAccessToken(code: String) {
        Log.d(TAG, "Storing access token...")
        LifecycleObservable.bindActivityLifecycle(lifecycle(), tokenMgr.grantAccessToken(code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    ToastUtils.show(BaseInjection.context, "Login failed. Please try again.")
                    finish()
                    startActivity(intent)
                }
                .subscribe {
                    UserUtils.saveName(it.username)
                    tokenMgr.storage.storeAccessToken(it)
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    Log.d(TAG, "AccessToken stored for user ${it.username}.")
                }
    }
}
