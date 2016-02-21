package net.treelzebub.zinepress.db

import android.content.ContentValues
import android.net.Uri
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/28/16
 */

interface IWriter<T : Serializable> {

    val parent: IDatabase<T>

    fun bulkInsert(uri: Uri, vararg items: T): Boolean
    fun addOrUpdate(vararg items: T): Boolean
    fun clear() {
        parent.apply {
            context.contentResolver.delete(uri(), null, null)
        }
    }
    fun toContentValues(item: T): ContentValues
}
