package com.aarujob.models;

/**
 * basic model of the categories resulting from the html analysis
 *
 * @author serignediaby
 * @version 1.0.0
 */
public class CategoryModel {

    private String name;
    private String link;

    /**
     * <b>Constructor</b>
     */
    public CategoryModel() {
    }

    /**
     * <b>Constructor</b>
     *
     * @param name
     * @param link
     */
    public CategoryModel(String name, String link) {
        this.name = name;
        this.link = link;
    }

    /**
     * get the value of the attribute name
     *
     * @return the value of the name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Change the value of the name
     *
     * @param name the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the value of the attribute link
     *
     * @return the value of the link as a string
     */
    public String getLink() {
        return link;
    }

    /**
     * Change the value of the link
     *
     * @param link the new value of link
     */
    public void setLink(String link) {
        this.link = link;
    }
}
