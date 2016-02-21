package net.treelzebub.zinepress.db

import android.content.Context
import android.database.Cursor
import android.net.Uri
import net.treelzebub.zinepress.db.zines.ZineQuery
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/8/16
 */
interface IDatabase<T : Serializable> {

    val context: Context

    fun uri(): Uri
    fun write(): IWriter<T>
    fun all(): List<T>
    fun query(): IQuery<T>
    fun cursor(query: IQuery<T>): Cursor
    fun list(query: IQuery<T>): List<T>
}
