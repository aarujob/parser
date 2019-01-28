/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aarujob.parseurhtml.parsers.igfm.presenters;

import com.aarujob.parseurhtml.presenters.ArticleSeeker;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author serignediaby
 */
public class IgfmArticleSeeker extends  ArticleSeeker  {


    public IgfmArticleSeeker (Element element, boolean isHome, String name) {
        super(element, isHome, name);
    }

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

}
