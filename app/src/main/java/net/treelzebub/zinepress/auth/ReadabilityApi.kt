package net.treelzebub.zinepress.auth

import net.treelzebub.zinepress.api.model.Article
import net.treelzebub.zinepress.api.model.User
import retrofit.http.GET
import retrofit.http.Path

/**
 * Created by Tre Murillo on 1/2/16
 */
interface ReadabilityApi {

    @GET("/users/_current")
    fun identity(): User

    @GET("/articles/{article_id}")
    fun articles(@Path("article_id") id: String): List<Article>

    // ...
}
