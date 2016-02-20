package net.treelzebub.zinepress.ui.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import rx.Observable
import rx.Scheduler
import rx.android.lifecycle.LifecycleEvent
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject

/**
 * Created by Tre Murillo on 1/2/16
 */
open class BaseRxActivity : AppCompatActivity() {

    companion object {

        fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
        fun handler():    Scheduler = AndroidSchedulers.handlerThread(Handler())
    }

    private final val lifecycleSubject = BehaviorSubject.create<LifecycleEvent>()

    public fun lifecycle(): Observable<LifecycleEvent> {
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
