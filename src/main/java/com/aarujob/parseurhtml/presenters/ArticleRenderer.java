package com.aarujob.parseurhtml.presenters;

import java.util.Date;

import com.aarujob.models.ArticleModel;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;

import org.jsoup.nodes.Element;

/**
 * transforms an article element in html format into articleModel
 *
 * @author serignediaby
 * @version 1.0.0
 */
public abstract class ArticleRenderer {

    protected Element element;
    protected boolean isHome;
    protected String name;

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to an article html
     * @param isHome is true when article comes from home page
     * @param name the name of website
     */
    public ArticleRenderer(Element element, boolean isHome, String name) {
        this.element = element;
        this.isHome = isHome;
        this.name = name;
    }

    /**
     * makes the articleModel corresponding to the attribute element
     *
     * @return an object ArticleModel
     */
    public ArticleModel renderArticle() {
        ArticleModel article = new ArticleModel();
        article.setAuthor(renderAuthor());
        article.setUrl(renderUrl());
        article.setImage(renderImage());
        article.setDescription(renderDescription());
        article.setSiteName(name);
        article.setSiteUrl(PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())));
        String unformattedDate = renderUnformattedDate();
        article.setUnformattedPublicationDate(unformattedDate);
        article.setPublicationDate(renderPublicationDate(unformattedDate));

        return article;
    }

    /**
     * Convert unformatted date publication to a Date object
     *
     * @param unformattedDate unformatted publication date
     * @return the date corresponding to unformattedDate as Date
     */
    protected abstract Date renderPublicationDate(String unformattedDate);

    /**
     * retrieve the unformatted date that is in the element attribute
     *
     * @return unformatted date as a string
     */
    protected abstract String renderUnformattedDate();

    /**
     * retrieve the url that is in the element attribute
     *
     * @return url as a string
     */
    protected abstract String renderUrl();

    /**
     * retrieve the description that is in the element attribute
     *
     * @return url as a string
     */
    protected abstract String renderDescription();

    /**
     * retrieve the author that is in the element attribute
     *
     * @return description as a string
     */
    protected abstract String renderAuthor();

    /**
     * retrieve the image that is in the element attribute
     *
     * @return location of image as a string
     */
    protected abstract String renderImage();

}
