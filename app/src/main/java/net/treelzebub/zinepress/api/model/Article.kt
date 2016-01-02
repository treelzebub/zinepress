package net.treelzebub.zinepress.api.model

import org.joda.time.LocalDate

/**
 * Created by Tre Murillo on 1/2/16
 */
data class Article(
        val domain: String?,
        val nextPageHref: String?,
        val author: String?,
        val url: String?,
        val leadImageUrl: String?,
        val contentSize: Long?,
        val title: String?,
        val excerpt: String?,
        val direction: String?,
        val wordCount: Long?,
        val content: String?,
        val datePublished: LocalDate,
        val dek: String?,
        val processed: Boolean?,
        val shortUrl: String?,
        val id: String?
)