package net.treelzebub.zinepress.db

import android.database.Cursor
import android.database.DatabaseUtils
import net.treelzebub.zinepress.util.pairOf

/**
 * Created by Tre Murillo on 1/28/16
 */
interface IQuery<T> {

    val parent: ZinepressDatabase<T>

    fun list(): List<T>
    fun cursor(): Cursor
    fun selection(): Pair<String, Array<String>>

    fun formatSelection(vararg strPairs: Pair<String, String?>): Pair<String, Array<String>> {
        var selection = ""
        val args = strPairs.mapNotNull { it.second }.toTypedArray()
        strPairs.forEach {
            selection = DatabaseUtils.concatenateWhere(selection, it.first)
        }
        return pairOf(selection, args)
    }
}
