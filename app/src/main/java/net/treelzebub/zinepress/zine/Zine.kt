package net.treelzebub.zinepress.zine

import nl.siegmann.epublib.domain.Resource
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/8/16
 */
class Zine : Serializable {

    var date: Long
    var zineArticles: List<ZineArticle>

    var title: String?        = null
    var coverImage: Resource? = null

    constructor(date: Long, zineArticles: List<ZineArticle>) {
        this.date         = date
        this.zineArticles = zineArticles
    }

    constructor(date: Long, zineArticles: List<ZineArticle>, title: String, coverImage: Resource) {
        this.date         = date
        this.zineArticles = zineArticles
        this.title        = title
        this.coverImage   = coverImage
    }
}
