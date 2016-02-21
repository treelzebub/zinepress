package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import net.treelzebub.zinepress.auth.PocketTokenManager
import rx.android.lifecycle.LifecycleObservable

/**
 * Created by Tre Murillo on 2/20/16
 */
open class AuthedRxActivity : BaseRxActivity() {

    override fun onResume() {
        super.onResume()
        val hasToken = PocketTokenManager.from(this).storage.hasAccessToken()
        LifecycleObservable.bindActivityLifecycle(lifecycle(), hasToken)
            .doOnNext {
                if (!it) {
                    startActivity(Intent(this@AuthedRxActivity, LoginActivity::class.java))
                }
            }
    }
}
