package com.aarujob.models;

import java.util.Date;

/**
 * basic model of the articles resulting from the html analysis
 *
 * @author smag
 * @version 1.0.0
 */
public class ArticleModel {

    private String id;

    private String siteName;

    private String url;

    private String image;

    private String description;

    private String siteUrl;

    private String author;

    private String unformattedPublicationDate;

    private Date publicationDate;

    private Date parsed_at;

    private CategoryModel category;

    /**
     * <b>Constructor</b>
     */
    public ArticleModel() {
        this.parsed_at = new Date();
        this.id = "ART" + System.nanoTime();
    }

    /**
     * get the value of attribute id
     *
     * @return the identification of an articleModel as a string
     */
    public String getId() {
        return id;
    }

    /**
     * Change the value of the id
     *
     * @param id the new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the value of attribute siteName
     *
     * @return the value's site name of article as a string
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Change the value of the siteName
     *
     * @param siteName
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * get the value of attribute
     *
     * @return the value of the article url as a string
     */
    public String getUrl() {
        return url;
    }

    /**
     * change the value of the url
     *
     * @param url the new value
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get the value of the image
     *
     * @return the location of image as a string
     */
    public String getImage() {
        return image;
    }

    /**
     * Change the value of the image
     *
     * @param image the new value of image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * get the value of attribute description
     *
     * @return the value of the article description as a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change the value of the description
     *
     * @param description the new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of siteUrl
     *
     * @return the site url of article as a string
     */
    public String getSiteUrl() {
        return siteUrl;
    }

    /**
     * Change the value of the site url
     *
     * @param siteUrl
     */
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    /**
     * get the value of the attribute
     *
     * @return the article author as a string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Change the value of the author
     *
     * @param author the new value
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * get the value of the attribute unformattedPublicationDate
     *
     * @return the unformatted publication date of article as a string
     */
    public String getUnformattedPublicationDate() {
        return unformattedPublicationDate;
    }

    /**
     * Change the value of the unformatted publication date as a string
     *
     * @param unformattedPublicationDate
     */
    public void setUnformattedPublicationDate(String unformattedPublicationDate) {
        this.unformattedPublicationDate = unformattedPublicationDate;
    }

    /**
     * get the value of the attribute
     *
     * @return the publication date as a Date
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Change the value of the publication date
     *
     * @param publicationDate the new value
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * get the value of the attribute parsed_at
     *
     * @return the moment for parsing as a <b>Date</b>
     */
    public Date getParsed_at() {
        return parsed_at;
    }

    /**
     * Change the value of the moment for parsing
     *
     * @param parsed_at the new value
     */
    public void setParsed_at(Date parsed_at) {
        this.parsed_at = parsed_at;
    }

    /**
     * get the value of the category
     *
     * @return category of article as <b>CategoryModel</b>
     */
    public CategoryModel getCategoryModel() {
        return category;
    }

    /**
     * Change the value of the category of article
     *
     * @param category the new value of category
     */
    public void setCategoryModel(CategoryModel category) {
        this.category = category;
    }

}
