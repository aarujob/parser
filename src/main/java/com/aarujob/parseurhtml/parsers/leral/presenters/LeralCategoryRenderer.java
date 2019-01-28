package com.aarujob.parseurhtml.parsers.leral.presenters;

import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.presenters.CategoryRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import org.jsoup.nodes.Element;

/**
 * transforms a category element of Leral website into CategoryModel object
 *
 * @author smag
 * @version 1.0.0
 */
public class LeralCategoryRenderer extends CategoryRenderer {

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to a catergory html of Leral website
     * @param isHome  is true when article comes from home page
     * @param name    the name of website
     */
    public LeralCategoryRenderer(Element element, String name) {
        super(element, name);
    }

    /**
     * makes the CategoryModel corresponding to the attribute element
     *
     * @return an object CategoryModel
     */
    @Override
    public CategoryModel renderCategory() {
        CategoryModel category = null;
        try {
            Element aTag = element
                    .selectFirst(PropertiesFactory.getProperty(name.concat(HomeProperties.CATEGORY_ITEM.toString())));
            String name = renderName(aTag);
            String link = renderLink(aTag);
            category = new CategoryModel(name, link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * retrieve the name of category that is in the element attribute
     *
     * @return name of category as a string
     */
    @Override
    protected String renderName(Element element) {
        String name = null;
        try {
            name = element.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * retrieve the link that is in the element attribute
     *
     * @return link of article as a string
     */
    @Override
    protected String renderLink(Element element) {
        String link = null;
        try {
            link = element.attr("href");
            link = link.contains("http") ? link
                    : PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return link;
    }
}