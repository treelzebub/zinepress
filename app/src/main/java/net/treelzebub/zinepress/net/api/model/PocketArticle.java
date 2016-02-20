package net.treelzebub.zinepress.net.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tre Murillo on 1/3/16
 */
public class PocketArticle {

    @SerializedName("item_id")
    private final long id;

    @SerializedName("time_added")
    private final long date;

    @SerializedName("resolved_title")
    private final String title;

    @SerializedName("resolved_url")
    private final String url;

    PocketArticle(long id, long date, String title, String url) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.url = url;
    }

    public long getDate() {
        return date;
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
