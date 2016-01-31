package net.treelzebub.zinepress.zine

import net.treelzebub.zinepress.db.articles.IArticle
import java.util.*

/**
 * Created by Tre Murillo on 1/3/16
 */
object SelectedArticles {

    // Currently selected Articles to collect as ePub
    val articles: HashSet<IArticle> = hashSetOf()
}
