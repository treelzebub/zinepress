package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import net.treelzebub.zinepress.auth.PocketTokenManager
import rx.android.schedulers.AndroidSchedulers
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 1/2/16
 */
class MainActivity : BaseRxActivity() {

    private var tokenManager: PocketTokenManager by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = PocketTokenManager.from(this)
        tokenManager.storage.hasAccessToken()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { loggedIn ->
                        if (loggedIn) {
                            startActivity(Intent(this, DashboardActivity::class.java))
                        } else {
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                    }
    }
}
