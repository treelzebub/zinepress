package net.treelzebub.zinepress.db

import android.content.Context
import net.treelzebub.zinepress.util.BaseInjection

/**
 * Created by Tre Murillo on 1/8/16
 */
interface Zines {

    companion object {
        private var singleton: Zines? = null

        @Synchronized
        fun get(c: Context = BaseInjection.context): Zines {
            if (singleton == null) {
                singleton = DbZines(c)
            }
            return singleton!!
        }
    }

    val context: Context

    fun query(): ZineQuery
    fun write(): ZineWriter
    fun listen(listener: ZineListener)
    fun unlisten(listener: ZineListener)
}
