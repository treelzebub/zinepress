package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.treelzebub.zinepress.auth.PocketTokenManager
import rx.Observable
import rx.android.lifecycle.LifecycleEvent
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 1/2/16
 */
open class BaseRxActivity : AppCompatActivity() {

    public var token: String by Delegates.notNull()
    private final val lifecycleSubject = BehaviorSubject.create<LifecycleEvent>()

    public fun lifecycle(): Observable<LifecycleEvent> {
        return lifecycleSubject.asObservable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(LifecycleEvent.CREATE)
        val storage = PocketTokenManager.from(this).storage
        storage.hasAccessToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { loggedIn ->
                    if (loggedIn) {
                        storage.storedAccessToken
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    token = it.accessToken
                                }
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }

    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(LifecycleEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(LifecycleEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(LifecycleEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY)
        super.onDestroy()
    }
}
