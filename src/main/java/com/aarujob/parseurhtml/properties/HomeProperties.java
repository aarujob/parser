package com.aarujob.parseurhtml.properties;

/**
 * contains a part of the properties which will be used for the analysis of
 * <br/>the home page of the information sites. Each enum element concatenate
 * with the<br/> name of a site defines a <b>key in the properties file<b/>.
 *
 * @author smag
 * @version 1.0.0
 */
public enum HomeProperties {
    URL(".url"), CATEGORY(".home.category"), CATEGORY_ITEM(".home.category.item"), ARTICLE(".home.article"),
    ARTICLE_ITEM(".home.article.item"), IMAGE(".home.article.item.image"), AUTHOR(".home.article.item.author"),
    DESCRIPTION(".home.article.item.description"), UNFORMATTED_DATE(".home.article.item.unformattedDatePublication");

    private String propertyName = "";

    HomeProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toString() {
        return this.propertyName;
    }

}
