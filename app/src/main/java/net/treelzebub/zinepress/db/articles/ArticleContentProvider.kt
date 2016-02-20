package net.treelzebub.zinepress.db.articles

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
 * Created by Tre Murillo on 1/31/16
 */
class ArticleContentProvider : ContentProvider() {

    private val helper: ArticlesSQLiteHelper by lazy { ArticlesSQLiteHelper(context) }
    private val readDb: SQLiteDatabase       get() = helper.readableDatabase
    private val writeDb: SQLiteDatabase      get() = helper.writableDatabase

    override fun onCreate() = true

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        do {
            try {
                return readDb.query(ArticleCols._TABLE, projection, selection, selectionArgs, null, null, sortOrder)
            } catch (_: SQLiteDatabaseLockedException) {
                Log.e(TAG, "db locked")
            }
        } while (true)
        throw RuntimeException("Impossibru!")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        try {
            writeDb.insertWithOnConflict(ArticleCols._TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
        }
        return uri
    }

    override fun bulkInsert(uri: Uri, values: Array<out ContentValues>?): Int {
        writeDb.delete(ArticleCols._TABLE, null, null)
        var count = 0
        values?.forEach {
            insert(uri, it)
            ++count
        }
        Log.d(TAG, "Inserted $count items.")
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.update(ArticleCols._TABLE, values, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        try {
            return writeDb.delete(ArticleCols._TABLE, selection, selectionArgs)
        } catch (e: SQLiteException) {
            Log.e(TAG, e.message)
            return 0
        }
    }

    override fun getType(uri: Uri): String? = null
}
