package net.treelzebub.zinepress.db.articles

import android.provider.BaseColumns

/**
 * Created by Tre Murillo on 1/28/16
 */
object ArticleCols : BaseColumns {

    val _TABLE = "articles_table"

    val ID = "id"
    val DATE = "date"
    val TITLE = "title"
    val URL = "url"
}
