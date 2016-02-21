package net.treelzebub.zinepress.db.zines

import net.treelzebub.zinepress.db.BaseContentProvider

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineContentProvider : BaseContentProvider(ZineCols._TABLE) {

    override fun onCreate(): Boolean {
        helper = ZineSQLiteHelper(context)
        return super.onCreate()
    }
}
