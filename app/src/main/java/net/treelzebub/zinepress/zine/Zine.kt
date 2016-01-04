package net.treelzebub.zinepress.zine

import nl.siegmann.epublib.domain.Resource

/**
 * Created by Tre Murillo on 1/3/16
 */
class Zine(var title: String?,
           var coverImage: Resource?,
           val zineArticles: List<ZineArticle>)
