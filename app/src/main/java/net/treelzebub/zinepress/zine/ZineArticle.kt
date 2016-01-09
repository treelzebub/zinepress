package net.treelzebub.zinepress.zine

import java.io.Serializable

/**
 * Created by Tre Murillo on 1/3/16
 */
class ZineArticle(val url: String,
              val title: String,
              val rawHtml: String) : Serializable