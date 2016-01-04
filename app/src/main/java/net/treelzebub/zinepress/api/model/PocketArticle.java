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

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return "https://getpocket/a/read/" + id;
    } 

    public long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return url;
    }
}
