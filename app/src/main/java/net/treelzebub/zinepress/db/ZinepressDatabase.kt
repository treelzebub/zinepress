package net.treelzebub.zinepress.db

import android.content.Context
import android.database.Cursor
import net.treelzebub.zinepress.db.zines.ZineQuery

/**
 * Created by Tre Murillo on 1/8/16
 */
interface ZinepressDatabase<T> {

    val context: Context

    fun write(): IWriter<T>
    fun all(): List<T>
    fun query(): IQuery<T>
    fun cursor(query: IQuery<T>): Cursor
    fun list(query: IQuery<T>): List<T>
}
