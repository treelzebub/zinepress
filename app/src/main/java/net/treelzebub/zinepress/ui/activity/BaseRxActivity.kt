package net.treelzebub.zinepress.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.Observable
import rx.android.lifecycle.LifecycleEvent
import rx.subjects.BehaviorSubject

/**
 * Created by Tre Murillo on 1/2/16
 */
open class BaseRxActivity : AppCompatActivity() {

    private final val lifecycleSubject = BehaviorSubject.create<LifecycleEvent>()

    fun lifecycle(): Observable<LifecycleEvent> {
        return lifecycleSubject.asObservable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(LifecycleEvent.CREATE)
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
