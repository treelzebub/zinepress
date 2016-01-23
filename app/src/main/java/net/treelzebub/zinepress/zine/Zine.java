package net.treelzebub.zinepress.zine;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.List;

import nl.siegmann.epublib.domain.Resource;

/**
 * Created by Tre Murillo on 1/8/16
 */
public class Zine implements Serializable {

    private Long              date;
    private String            title;
    private Resource          coverImage;
    private List<ZineArticle> zineArticles;

    public Zine(Long date, String title, Resource coverImage, List<ZineArticle> zineArticles) {
        this.date         = date;
        this.title        = title;
        this.coverImage   = coverImage;
        this.zineArticles = zineArticles;
    }

    public Long getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Resource getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Resource coverImage) {
        this.coverImage = coverImage;
    }

    public List<ZineArticle> getZineArticles() {
        return zineArticles;
    }

    public void setZineArticles(List<ZineArticle> zineArticles) {
        this.zineArticles = zineArticles;
    }
}
