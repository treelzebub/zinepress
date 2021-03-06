package net.treelzebub.zinepress.ui.activity

import android.util.Log
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.extensions.TAG
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Tre Murillo on 2/20/16
 */
open class AuthedRxActivity : BaseRxActivity() {

    override fun onResume() {
        super.onResume()
        LifecycleObservable.bindActivityLifecycle(lifecycle(),
                PocketTokenManager.from(this).storage.hasAccessToken())
                .subscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    hasToken ->
                    Log.d(TAG, "hasToken check: $hasToken")
                    if (!hasToken) {
                        startActivity(LoginActivity.intent(this))
                    }
                }
    }
}
