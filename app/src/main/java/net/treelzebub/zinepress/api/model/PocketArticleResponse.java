package net.treelzebub.zinepress.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Tre Murillo on 1/3/16
 */
public class PocketArticleResponse {

    @SerializedName("list")
    private final Map<String, PocketArticle> list;

    PocketArticleResponse(Map<String, PocketArticle> list) {
        this.list = list;
    }

    public Map<String, PocketArticle> getMap() {
        return list;
    }
}
