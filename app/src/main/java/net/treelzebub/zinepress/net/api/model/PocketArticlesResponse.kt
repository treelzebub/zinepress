package net.treelzebub.zinepress.net.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tre Murillo on 1/31/16
 */
data class PocketArticleResponse(@SerializedName("list")
                                 val articles: List<PocketArticle>)
