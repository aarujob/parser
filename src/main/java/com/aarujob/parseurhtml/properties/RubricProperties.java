package com.aarujob.parseurhtml.properties;

/**
 * contains a part of the properties which will be used for the analysis of
 * <br/>a specific page of the information sites. Each enum element concatenate
 * with the<br/> name of a site defines a <b>key in the properties file<b/>.
 *
 * @author smag
 * @version 1.0.0
 */
public enum RubricProperties {
    URL(".url"), CATEGORY(".rubric.category"), CATEGORY_ITEM(".rubric.category.item"), ARTICLE(".rubric.article"),
    ARTICLE_ITEM(".rubric.article.item"), IMAGE(".rubric.article.item.image"), AUTHOR(".rubric.article.item.author"),
    DESCRIPTION(".rubric.article.item.description"),
    UNFORMATTED_DATE(".rubric.article.item.unformattedDatePublication");

    private String propertyName = "";

    RubricProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toString() {
        return this.propertyName;
    }
    
}
