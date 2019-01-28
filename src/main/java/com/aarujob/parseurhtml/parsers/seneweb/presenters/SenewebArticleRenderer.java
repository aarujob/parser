package com.aarujob.parseurhtml.parsers.seneweb.presenters;

import com.aarujob.parseurhtml.presenters.ArticleRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.nodes.Element;

/**
 * transforms an article element of seneweb website into articleModel object
 *
 * @author smag
 * @version 1.0.0
 */
public class SenewebArticleRenderer extends ArticleRenderer {

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to an article html of seneweb website
     * @param isHome is true when article comes from home page
     * @param name the name of website
     */
    public SenewebArticleRenderer(Element element, boolean isHome, String name) {
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
        String[] tokens = null;
        String tokens_0;
        try {
            tokens = unformattedDatePublication.split(" ");
            tokens_0 = tokens[0];
        } catch (Exception ex) {
            tokens_0 = unformattedDatePublication;
        }

        try {
            if (tokens_0.contains(":")) {
                String[] time = tokens[0].split((":"));
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return cal.getTime();
            } else if (tokens[1].contains("heure")) {
                return Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(tokens[0]) * 60 * 60));
            } else if (tokens[1].contains("minute")) {
                return Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(tokens[0]) * 60));
            } else if (tokens[1].contains("Commentaire")) {
                return Timestamp.from(Instant.now().plusSeconds(-1 * 24 * 60 * 60));
            } else if (tokens[1].contains("jour")) {
                return Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(tokens[0]) * 24 * 60 * 60));
            } else if (tokens[1].contains("semaine")) {
                return Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(tokens[0]) * 7 * 24 * 60 * 60));
            } else if (tokens[1].contains("mois")) {
                return Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(tokens[0]) * 30 * 7 * 24 * 60 * 60));
            } else if (tokens[0].contains("maintenant") || tokens[1].contains("l'instant")) {
                return new Date();
            }
        } catch (Exception e) {
            //unformattedDatePublication is empty
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
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
            String finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.UNFORMATTED_DATE.toString())) : PropertiesFactory.getProperty(name.concat(RubricProperties.UNFORMATTED_DATE.toString()));
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
            String finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.ARTICLE_ITEM.toString())) : PropertiesFactory.getProperty(name.concat(RubricProperties.ARTICLE_ITEM.toString()));
            Element urlElement = element.selectFirst(finderProperty);
            url = urlElement.attr("href");
            url = url.contains("http://") ? url
                    : PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(url);
        } catch (Exception e) {
            // finderProperty isn't correct
            //e.printStackTrace();
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
            String finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.DESCRIPTION.toString())) : PropertiesFactory.getProperty(name.concat(RubricProperties.DESCRIPTION.toString()));
            Element descriptionElement = element.selectFirst(finderProperty);
            descriptionString = descriptionElement.text();
        } catch (Exception e) {
            //Article doesn't contain description.
            //e.printStackTrace();
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
            String finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.IMAGE.toString())) : PropertiesFactory.getProperty(name.concat(RubricProperties.IMAGE.toString()));
            Element imageElement = element.selectFirst(finderProperty);
            imageLocation = imageElement.attr("src");

        } catch (NullPointerException e) {
            // finderProperty isn't correct 
            // e.printStackTrace();
        }
        return imageLocation;
    }

}
