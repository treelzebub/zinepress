package net.treelzebub.zinepress.db

import android.content.ContentValues
import net.treelzebub.zinepress.util.extensions.maybePut
import net.treelzebub.zinepress.util.extensions.serialize
import net.treelzebub.zinepress.zine.Zine

/**
 * Created by Tre Murillo on 1/8/16
 */
fun zineToDB(zine: Zine, insertOnNull: Boolean = true): ContentValues {
    val retval = ContentValues()
    retval.maybePut(ZineCols.DATE,  zine.date,        insertOnNull)
    retval.maybePut(ZineCols.TITLE, zine.title,       insertOnNull)
    retval.maybePut(ZineCols.ZINE,  zine.serialize(), insertOnNull)
    return retval
}
