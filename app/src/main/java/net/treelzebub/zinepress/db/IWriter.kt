package net.treelzebub.zinepress.db

import android.content.ContentValues
import android.net.Uri

/**
 * Created by Tre Murillo on 1/28/16
 */

interface IWriter<T> {

    val parent: ZinepressDatabase<T>

    fun bulkInsert(uri: Uri, vararg items: T): Boolean
    fun addOrUpdate(vararg items: T): Boolean
    fun toContentValues(item: T): ContentValues
}
