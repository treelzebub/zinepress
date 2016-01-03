package net.treelzebub.zinepress.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tre Murillo on 1/3/16
 */
public class PocketArticle {

    @SerializedName("item_id")
    private final long id;

    @SerializedName("resolved_title")
    private final String title;

    @SerializedName("resolved_url")
    private final String url;

    PocketArticle(long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    String getTitle() {
        return title;
    }

    String getUrl() {
        return url;
    }

    long getId() {
        return id;
    }
}
