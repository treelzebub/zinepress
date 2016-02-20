package net.treelzebub.zinepress.zine

import java.io.Serializable

/**
 * Created by Tre Murillo on 1/3/16
 */
data class ZineArticle(
        val id: Long,
        val date: Long,
        val url: String,
        val title: String,
        val rawHtml: String) : Serializable