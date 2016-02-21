package net.treelzebub.zinepress.ui.activity

import android.os.Bundle
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.util.extensions.createIntent
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Tre Murillo on 1/2/16
 */
class MainActivity : BaseRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenMgr = PocketTokenManager.from(this)
        tokenMgr.storage.hasAccessToken()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { loggedIn ->
                        if (loggedIn) {
                            startActivity(createIntent<DashboardActivity>())
                        } else {
                            startActivity(createIntent<LoginActivity>())
                        }
                    }
    }
}
