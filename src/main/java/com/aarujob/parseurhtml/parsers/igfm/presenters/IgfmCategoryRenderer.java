/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aarujob.parseurhtml.parsers.igfm.presenters;

import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.presenters.CategoryRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import org.jsoup.nodes.Element;

/**
 *
 * @author serignediaby
 */
public class IgfmCategoryRenderer extends CategoryRenderer {

    public IgfmCategoryRenderer(Element element, String name) {
        super(element, name);
    }

    @Override
    public CategoryModel renderCategory() {
        Element aTag = element.selectFirst(PropertiesFactory.getProperty(name.concat(HomeProperties.CATEGORY_ITEM.toString())));
        String name = renderName(aTag);
        String link = renderLink(aTag);
        return new CategoryModel(name, link);
    }

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

    @Override
    protected String renderLink(Element element) {
        String link = null;
        try {
            link = element.attr("href");
            if(!link.contains("http"))
                link = PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(link);
                } catch (Exception e) {
            e.printStackTrace();
        }
        return link;
    }

}
