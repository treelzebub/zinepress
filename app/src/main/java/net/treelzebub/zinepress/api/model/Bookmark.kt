package net.treelzebub.zinepress.api.model

import org.joda.time.LocalDate

/**
 * Created by Tre Murillo on 1/2/16
 */
data class Bookmark(
        val userId: Long,
        val readPercent: Double,
        val dateUpdated: LocalDate,
        val favorite: Boolean,
        val article: Article,
        val id: Long,
        val dateArchived: LocalDate?,
        val dateOpened: LocalDate?,
        val dateAdded: LocalDate?,
        val articleHref: String?,
        val dateFavorited: LocalDate?,
        val archive: Boolean,
        val tags: List<Tag>
)
