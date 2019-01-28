package com.aarujob.parseurhtml.parsers.walfnet.presenters;

import com.aarujob.parseurhtml.helpers.DateTimeHelper;
import com.aarujob.parseurhtml.presenters.ArticleRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.nodes.Element;

/**
 * transforms an article element of Walfnet website into articleModel object
 *
 * @author smag
 * @version 1.0.0
 */
public class WalfnetArticleRenderer extends ArticleRenderer {

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to an article html of Walfnet website
     * @param isHome  is true when article comes from home page
     * @param name    the name of website
     */
    public WalfnetArticleRenderer(Element element, boolean isHome, String name) {
        super(element, isHome, name);
    }

    /**
     * Convert unformatted date publication to a Date object
     *
     * @param unformattedDate unformatted publication date
     * @return the date corresponding to unformattedDate as Date
     */
    @Override
    protected Date renderPublicationDate(String unformattedDatePublication) {
        String[] tokens = unformattedDatePublication.split(" ");
        Calendar cal = Calendar.getInstance();
        try {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.YEAR, Integer.parseInt(tokens[2]));
            cal.set(Calendar.MONTH, DateTimeHelper.getMonth(tokens[1]));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[0]));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cal.getTime();
    }

    /**
     * retrieve the unformatted date that is in the element attribute
     *
     * @return unformatted date as a string
     */
    @Override
    protected String renderUnformattedDate() {
        String unformattedPublicationDate = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.UNFORMATTED_DATE.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.UNFORMATTED_DATE.toString()));
            Element unformattedPublicationDateElement = element.selectFirst(finderProperty);
            unformattedPublicationDate = unformattedPublicationDateElement.text();
            return unformattedPublicationDate;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return unformattedPublicationDate;
    }

    /**
     * retrieve the url that is in the element attribute
     *
     * @return url as a string
     */
    @Override
    protected String renderUrl() {
        String url = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.ARTICLE_ITEM.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.ARTICLE_ITEM.toString()));
            Element urlElement = element.selectFirst(finderProperty);
            url = urlElement.attr("href");
            url = url.contains("http") ? url
                    : PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(url);
        } catch (Exception e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        return url;
    }

    /**
     * retrieve the description that is in the element attribute
     *
     * @return url as a string
     */
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

    /**
     * retrieve the author that is in the element attribute
     *
     * @return location of author as a string
     */
    @Override
    protected String renderAuthor() {
        return null;
    }

    /**
     * retrieve the image url that is in the element attribute
     *
     * @return location of image as a string
     */
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