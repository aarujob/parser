/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aarujob.parseurhtml.parsers.igfm.presenters;

import com.aarujob.parseurhtml.presenters.ArticleRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jsoup.nodes.Element;

/**
 *
 * @author serignediaby
 */
public class IgfmArticleRenderer extends ArticleRenderer {

    public IgfmArticleRenderer(Element element, boolean isHome, String name) {
        super(element, isHome, name);
    }

    @Override
    protected Date renderPublicationDate(String unformattedDate) {
        Calendar cal = new GregorianCalendar();
        try {
            cal.set(Integer.parseInt(unformattedDate.substring(0, 4)),
                    Integer.parseInt(unformattedDate.substring(5, 7)) - 1,
                    Integer.parseInt(unformattedDate.substring(8, 10)));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(unformattedDate.substring(11, 13)));
            cal.set(Calendar.MINUTE, Integer.parseInt(unformattedDate.substring(14, 16)));
            cal.set(Calendar.SECOND, Integer.parseInt(unformattedDate.substring(17, 19)));
            cal.set(Calendar.MILLISECOND, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }

    @Override
    protected String renderUnformattedDate() {
        String unformattedPublicationDate = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.UNFORMATTED_DATE.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.UNFORMATTED_DATE.toString()));
            Element unformattedPublicationDateElement = element.selectFirst(finderProperty);
            unformattedPublicationDate = unformattedPublicationDateElement.attr("datetime");
            return unformattedPublicationDate;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return unformattedPublicationDate;
    }

    @Override
    protected String renderUrl() {
        String url = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.ARTICLE_ITEM.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.ARTICLE_ITEM.toString()));
            Element urlElement = element.selectFirst(finderProperty);
            url = urlElement.attr("href");
            url = url.contains("http://") ? url
                    : PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(url);
        } catch (Exception e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        return url;
    }

    @Override
    protected String renderDescription() {
        String descriptionString = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.DESCRIPTION.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.DESCRIPTION.toString()));
            Element descriptionElement = element.selectFirst(finderProperty);
            descriptionString = descriptionElement.text();
        } catch (Exception e) {
            // Article doesn't contain description.
            // e.printStackTrace();
        }
        return descriptionString;
    }

    @Override
    protected String renderAuthor() {
        return null;
    }

    @Override
    protected String renderImage() {
        String imageLocation = null;
        try {
            String finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.IMAGE.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.IMAGE.toString()));
            Element imageElement = element.selectFirst(finderProperty);
            imageLocation = imageElement.attr("src");

        } catch (NullPointerException e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        return imageLocation;
    }

}
