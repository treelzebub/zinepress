package net.treelzebub.zinepress.db.articles

/**
 * Created by Tre Murillo on 1/28/16
 */
interface IArticle {
    val id: Long
    val date: Long
    val title: String
    val originalUrl: String
}
