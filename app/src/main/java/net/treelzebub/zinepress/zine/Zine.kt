package net.treelzebub.zinepress.zine

import net.treelzebub.zinepress.db.zines.IZine
import net.treelzebub.zinepress.util.bytes
import nl.siegmann.epublib.domain.Resource
import java.io.Serializable
import java.util.*

/**
 * Created by Tre Murillo on 1/8/16
 */
class Zine : IZine {

    override val id: Long
    override val date: Long
    override val title: String
    override val articles: ByteArray

    constructor(id: Long, date: Long, title: String, zineArticles: HashSet<ZineArticle>) {
        this.id           = id
        this.date         = date
        this.title        = title
        this.articles     = zineArticles.bytes()
    }
}
