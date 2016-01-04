package net.treelzebub.zinepress.zine

import net.treelzebub.zinepress.api.model.PocketArticle
import java.util.*

/**
 * Created by Tre Murillo on 1/3/16
 */
object SelectedArticles {

    // List of urls to collect as ePub
    private val articles: ArrayList<PocketArticle> = arrayListOf()

    fun add(article: PocketArticle) {
        articles.add(article)
    }

    fun remove(article: PocketArticle) {
        articles.remove(article)
    }

    fun list(): List<PocketArticle> {
        return articles
    }
}
