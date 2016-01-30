package net.treelzebub.zinepress

import android.app.Application
import net.treelzebub.zinepress.util.BaseInjection

/**
 * Created by Tre Murillo on 1/2/16
 */
class ZinePressApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseInjection.context = this.applicationContext
        ZinePressInit.init(this)
    }
}
