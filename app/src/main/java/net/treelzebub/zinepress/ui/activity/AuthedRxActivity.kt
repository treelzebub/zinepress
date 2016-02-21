package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.util.Log
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.extensions.TAG
import rx.android.lifecycle.LifecycleObservable

/**
 * Created by Tre Murillo on 2/20/16
 */
open class AuthedRxActivity : BaseRxActivity() {

    override fun onResume() {
        super.onResume()
        LifecycleObservable.bindActivityLifecycle(lifecycle(),
                PocketTokenManager.from(this).storage.hasAccessToken())
                .subscribe({
                    hasToken ->
                    Log.d(TAG, "hasToken check: $hasToken")
                    if (!hasToken) {
                        showLogin()
                    }
                }, {
                    showLogin()
                })
    }

    private fun showLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
