package net.treelzebub.zinepress.db.articles

import android.content.ContentValues
import net.treelzebub.zinepress.util.extensions.maybePut

/**
 * Created by Tre Murillo on 1/28/16
 */
interface IArticle {

    val id: Long
    val date: Long
    val title: String
    val originalUrl: String

    fun pocketUrl(): String {
        return "https://getpocket/a/read/$id"
    }
}

fun IArticle.toContentValues(): ContentValues {
    val retval = ContentValues()
    retval.maybePut(ArticleCols.ID,    id)
    retval.maybePut(ArticleCols.DATE,  date)
    retval.maybePut(ArticleCols.TITLE, title)
    retval.maybePut(ArticleCols.URL,   originalUrl)
    return retval
}
