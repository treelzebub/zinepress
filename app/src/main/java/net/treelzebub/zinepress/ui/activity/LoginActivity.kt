package net.treelzebub.zinepress.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.util.ToastUtils
import net.treelzebub.zinepress.util.UserUtils
import net.treelzebub.zinepress.util.extensions.TAG
import net.treelzebub.zinepress.util.extensions.createIntent
import net.treelzebub.zinepress.util.extensions.setGone
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.web_view as webView

/**
 * Created by Tre Murillo on 1/2/16
 */
class LoginActivity : BaseRxActivity() {

    companion object {
        fun intent(c: Context): Intent {
            return Intent(c, LoginActivity::class.java)
        }
    }

    private val tokenMgr: PocketTokenManager get() = PocketTokenManager.from(this@LoginActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupWebView()
        requestToken()
    }

    private fun setupWebView() {
        webView.apply {
            clearFormData()
            clearHistory()
            clearCache(true)
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
        LifecycleObservable.bindActivityLifecycle(lifecycle(), tokenMgr.requestToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "RequestToken received with hash code ${it.hashCode()}.")
                    tokenMgr.saveRequestToken(it.code)
                    webView.loadUrl(tokenMgr.authUrl(it.code))
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
                    startActivity(createIntent<DashboardActivity>())
                    Log.d(TAG, "AccessToken stored for user ${it.username}.")
                }
    }
}
