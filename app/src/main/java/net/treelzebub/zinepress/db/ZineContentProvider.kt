package net.treelzebub.zinepress.db

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.util.Log
import net.treelzebub.zinepress.db.ZineCols.*
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 1/8/16
 */
class ZineContentProvider(context: Context) : ContentProvider() {

    companion object {
        val TAG = ZineContentProvider::class.java.simpleName
    }

    private val helper = ZineSQLiteHelper(context)
    private var readDb: SQLiteDatabase  by Delegates.notNull()
    private var writeDb: SQLiteDatabase by Delegates.notNull()

    override fun onCreate(): Boolean {
        readDb  = helper.readableDatabase
        writeDb = helper.writableDatabase
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        do {
            try {
                return readDb.query(_TABLE, projection, selection, selectionArgs, null, null, sortOrder)
            } catch (_: SQLiteDatabaseLockedException) {
                Log.e(TAG, "db locked")
            }
        } while (true)
        throw RuntimeException("Impossibru!")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        try {
            writeDb.insertWithOnConflict(_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        } catch (e: SQLiteException) {
            Log.e("Insert Error", e.message)
        }
        return uri
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.update(_TABLE, values, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.delete(_TABLE, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun getType(uri: Uri): String? = null
}