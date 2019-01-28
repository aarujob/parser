package com.aarujob.parseurhtml.presenters;

import com.aarujob.models.CategoryModel;
import org.jsoup.nodes.Element;

/**
 * transforms an category element in html format into CategoryModel
 *
 * @author serignediaby
 * @version 1.0.0
 */
public abstract class CategoryRenderer {

    protected Element element;
    protected String name;

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to an category html
     * @param name the name of website
     */
    public CategoryRenderer(Element element, String name) {
        this.element = element;
        this.name = name;
    }

    /**
     * makes the CategoryModel corresponding to the attribute element
     *
     * @return an object CategoryModel
     */
    public abstract CategoryModel renderCategory ();

    /**
     * retrieve the name of category that is in the element attribute
     *
     * @return name of category as a string
     */
    protected abstract String renderName(Element element);

    /**
     * retrieve the link that is in the element attribute
     *
     * @return link of article as a string
     */
    protected abstract String renderLink(Element element);

}
