package net.treelzebub.zinepress.api.model;

import com.google.gson.annotations.SerializedName;

import net.treelzebub.zinepress.db.articles.IArticle;

/**
 * Created by Tre Murillo on 1/3/16
 */
public class PocketArticle implements IArticle {

    @SerializedName("item_id")
    long id;

    @SerializedName("date")
    private final long date;

    @SerializedName("resolved_title")
    private final String title;

    @SerializedName("resolved_url")
    private final String originalUrl;

    PocketArticle(long id, long date, String title, String url) {
        this.id          = id;
        this.date        = date;
        this.title       = title;
        this.originalUrl = url;
    }

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
