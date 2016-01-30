package net.treelzebub.zinepress.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tre Murillo on 1/3/16
 */
public class PocketArticleResponse {

    @SerializedName("list")
    private final List<PocketArticle> articles;

    PocketArticleResponse(List<PocketArticle> articles) {
        this.articles = articles;
    }

    public List<PocketArticle> getArticles() {
        return articles;
    }
}
