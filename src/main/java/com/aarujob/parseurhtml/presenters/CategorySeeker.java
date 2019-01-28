package com.aarujob.parseurhtml.presenters;

import com.aarujob.parseurhtml.exceptions.CategorySeekerException;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * retrieves all categories in an element
 *
 * @author serignediaby
 * @version 1.0.0
 */
public abstract class CategorySeeker {

    protected Element element;
    protected String name;

    /**
     *<b>Constructor</b>
     * @param element references element html
     * @param name the name of website
     */
    public CategorySeeker(Element element, String name) {
        this.element = element;
        this.name = name;
    }

    /**
     * retrieves all categories html in the element attribute
     * @return elements category html
     */
    public Elements seekCategories() {
        Elements categories = null;
        try {
            categories = element.select(PropertiesFactory.getProperty(name.concat(HomeProperties.CATEGORY.toString())));
        } catch (CategorySeekerException e) {
            e.printStackTrace();
        }
        return categories;
    }

}