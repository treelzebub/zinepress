package net.treelzebub.zinepress.db.articles

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.util.Log
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.util.BaseInjection
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 1/31/16
 */
class ArticleContentProvider : ContentProvider() {

        companion object {
            val TAG = ArticleContentProvider::class.java.simpleName
            fun uri(c: Context = BaseInjection.context, query: String? = null): Uri {
                return Uri.Builder()
                        .scheme("content")
                        .authority(c.getString(R.string.authority_zines))
                        .query(query)
                        .build()
            }
        }

        private val helper: ArticlesSQLiteHelper by lazy { ArticlesSQLiteHelper(context) }
        private val readDb: SQLiteDatabase       get() = helper.readableDatabase
        private val writeDb: SQLiteDatabase      get() = helper.writableDatabase

        override fun onCreate(): Boolean {
            return true
        }

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
                Log.e("Insert Error", e.message)
            }
            return uri
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
