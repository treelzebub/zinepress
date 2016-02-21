package net.treelzebub.zinepress.net.api.model

import com.google.gson.annotations.SerializedName
import net.treelzebub.zinepress.db.articles.IArticle

/**
 * Created by Tre Murillo on 2/20/16
 */
class PocketArticle : IArticle {

    @SerializedName("item_id")
    override val id: Long

    @SerializedName("time_added")
    override val date: Long

    @SerializedName("resolved_title")
    override val title: String

    @SerializedName("resolved_url")
    override val originalUrl: String

    val url: String
        get() = "https://getpocket/a/read/$id"

    constructor(id: Long, date: Long, title: String, originalUrl: String) {
        this.id          = id
        this.date        = date
        this.title       = title
        this.originalUrl = originalUrl
    }
}
