package net.treelzebub.zinepress.db

import android.content.Context
import java.util.*

/**
 * Created by Tre Murillo on 1/8/16
 */
class DbZines(override val context: Context) : Zines {

    private val listeners = LinkedHashSet<ZineListener>()

    override fun query(): ZineQuery = ZineQuery(this)

    override fun write(): ZineWriter = ZineWriter(this)

    override fun listen(listener: ZineListener) {
        listeners.add(listener)
    }

    override fun unlisten(listener: ZineListener) {
        listeners.remove(listener)
    }
}
