package net.treelzebub.zinepress.db

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.util.Log
import net.treelzebub.zinepress.util.extensions.TAG
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 2/20/16
 */
open class BaseContentProvider(val table: String) : ContentProvider() {

    var helper: SQLiteOpenHelper by Delegates.notNull()

    private val readDb: SQLiteDatabase get()  = helper.readableDatabase
    private val writeDb: SQLiteDatabase get() = helper.writableDatabase

    override fun onCreate() = true

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        try {
            writeDb.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
        }
        Log.d(TAG, "Wrote ${values?.size() ?: 0} items to $table.")
        return uri
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.update(table, values, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun bulkInsert(uri: Uri, values: Array<out ContentValues>?): Int {
        writeDb.delete(table, null, null)
        var count = 0
        values?.forEach {
            insert(uri, it)
            ++count
        }
        Log.d(TAG, "Inserted $count items.")
        return count
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        do {
            try {
                return readDb.query(table, projection, selection, selectionArgs, null, null, sortOrder)
            } catch (_: SQLiteDatabaseLockedException) {
                Log.e(TAG, "db locked")
            }
        } while (true)
        throw RuntimeException("Impossibru!")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.delete(table, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
