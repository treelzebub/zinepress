package net.treelzebub.zinepress.db.articles

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.treelzebub.zinepress.db.articles.ArticleCols.*

/**
 * Created by Tre Murillo on 1/31/16
 */
class ArticlesSQLiteHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {

    companion object {
        const val DB_VER  = 1
        const val DB_NAME = "articles.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val cols = "$_ID INTEGER PRIMARY KEY AUTOINCREMENT, $DATE INTEGER, $TITLE TEXT, $URL TEXT"
        db.execSQL("CREATE TABLE IF NOT EXISTS $_TABLE ($cols)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$_TABLE'")
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
