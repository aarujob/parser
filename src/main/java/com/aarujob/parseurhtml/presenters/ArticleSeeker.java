package com.aarujob.parseurhtml.presenters;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * retrieves all articles in an element
 *
 * @author serignediaby
 * @version 1.0.0
 */
public abstract class ArticleSeeker {

    protected Element element;
    protected boolean isHome;
    protected String name;

    /**
     * <b>Constructor</b>
     *
     * @param element references element html
     * @param isHome is true when the element comes from homepage
     * @param name the name of website
     */
    public ArticleSeeker(Element element, boolean isHome, String name) {
        this.element = element;
        this.isHome = isHome;
        this.name = name;
    }

    /**
     * retrieves all articles html in the element attribute
     *
     * @return elements articles html
     */
    public Elements seekArticles() {
        return isHome ? seekHomeArticles() : seekRubricArticles();
    }

    /**
     * retrieves all articles in the element attribute when element doesn't com
     * from homepage
     *
     * @return elements articles html
     */
    protected abstract Elements seekRubricArticles();

    /**
     * retrieves all articles in the element attribute when element comes from
     * homepage
     *
     * @return elements articles html
     */
    protected abstract Elements seekHomeArticles();
}
