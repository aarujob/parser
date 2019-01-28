package com.aarujob.parseurhtml.parsers.xibar.presenters;

import com.aarujob.parseurhtml.presenters.ArticleSeeker;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * retrieves all articles in an element html which has provided by Xibar
 * website
 *
 * @author smag
 * @version 1.0.0
 */
public class XibarArticleSeeker extends ArticleSeeker {

    /**
     * <b>Constructor</b>
     *
     * @param element references element html retrieved in Xibar website
     * @param isHome  is true when the element comes from homepage
     * @param name    the name of website
     */
    public XibarArticleSeeker(Element element, boolean isHome, String name) {
        super(element, isHome, name);
    }

    /**
     * retrieves all articles in the element attribute when element doesn't com from
     * homepage
     *
     * @return elements articles html
     */
    @Override
    protected Elements seekRubricArticles() {
        Elements elements = null;
        try {
            elements = element.select(PropertiesFactory.getProperty(name.concat(RubricProperties.ARTICLE.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    /**
     * retrieves all articles in the element attribute when element comes from
     * homepage
     *
     * @return elements articles html
     */
    @Override
    protected Elements seekHomeArticles() {
        Elements elements = null;
        try {
            elements = element.select(PropertiesFactory.getProperty(name.concat(HomeProperties.ARTICLE.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

}
