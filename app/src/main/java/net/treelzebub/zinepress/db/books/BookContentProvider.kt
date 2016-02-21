package net.treelzebub.zinepress.db.books

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.util.Log
import net.treelzebub.zinepress.util.extensions.TAG

/**
 * Created by Tre Murillo on 2/20/16
 */
class BookContentProvider : ContentProvider() {

    private val helper: BookSQLiteHelper by lazy { BookSQLiteHelper(context) }
    private val readDb: SQLiteDatabase   get() = helper.readableDatabase
    private val writeDb: SQLiteDatabase  get() = helper.writableDatabase

    override fun onCreate() = true

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        try {
            writeDb.insertWithOnConflict(BookCols._TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
        }
        Log.d(TAG, "Wrote ${values?.size() ?: 0} Books to db.")
        return uri
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.update(BookCols._TABLE, values, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        do {
            try {
                return readDb.query(BookCols._TABLE, projection, selection, selectionArgs, null, null, sortOrder)
            } catch (_: SQLiteDatabaseLockedException) {
                Log.e(TAG, "db locked")
            }
        } while (true)
        throw RuntimeException("Impossibru!")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.delete(BookCols._TABLE, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }
}