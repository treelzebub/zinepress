package net.treelzebub.zinepress.ui.activity

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketTokenManager
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
        val wvSettings = webView.settings
        wvSettings.builtInZoomControls = true
        wvSettings.javaScriptEnabled   = true
        authorize()
    }

    private fun authorize() {
        val manager = PocketTokenManager.from(this)
        val grant   = manager.newAuthCodeGrant()

        // Listen for the auth url to load
        LifecycleObservable.bindActivityLifecycle(lifecycle(), grant.authorizationUri())
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { webView.loadUrl(it) }

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                grant.onUriLoadedCommand.onNext(Uri.parse(url))
                // Hide redirect page from user
                if (url.startsWith(grant.redirectUri)) {
                    webView.visibility = View.GONE
                }
            }
        })

        // Begin auth and listen for success
        LifecycleObservable.bindActivityLifecycle(lifecycle(), manager.grantNewAccessToken(grant))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it != null) {
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        ToastUtils.show(this, "Error.") //TODO
                    }
                }
    }
}
