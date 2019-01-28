package com.aarujob.parseurhtml.parsers.senego.presenters;

import com.aarujob.parseurhtml.presenters.ArticleRenderer;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import com.aarujob.parseurhtml.properties.RubricProperties;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import org.jsoup.nodes.Element;


/**
 * transforms an article element of Senego website into articleModel object
 *
 * @author smag
 * @version 1.0.0
 */
public class SenegoArticleRenderer extends ArticleRenderer {

    /**
     * <b>Constructor</b>
     *
     * @param element corresponds to an article html of Senego website
     * @param isHome  is true when article comes from home page
     * @param name    the name of website
     */
    public SenegoArticleRenderer(Element element, boolean isHome, String name) {
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
        Date current = null;
        try {
            String[] tokens = unformattedDatePublication.split(" ");
            String duree = tokens[tokens.length - 1].toLowerCase();
            String dureeNum = tokens[tokens.length - 2];

            if (duree.equals("heures") || duree.equals("heure")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 60 * 60));
            } else if (duree.equals("minutes") || duree.equals("minute")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 60));
            } else if (duree.equals("secondes") || duree.equals("seconde")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Integer.parseInt(dureeNum)));
            } else if (duree.equals("jours") || duree.equals("jour")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 24 * 60 * 60));
            } else if (duree.equals("semaines") || duree.equals("semaine")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 7 * 24 * 60 * 60));
            } else if (duree.equals("mois")) {
                current = Timestamp.from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 30 * 7 * 24 * 60 * 60));
            } else if (duree.equals("an") || duree.equals("ans") || duree.equals("année") || duree.equals("années")) {
                current = Timestamp
                        .from(Instant.now().plusSeconds(-Long.parseLong(dureeNum) * 12 * 30 * 7 * 24 * 60 * 60));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return current;
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
        String link = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.ARTICLE_ITEM.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.ARTICLE_ITEM.toString()));
            link = element.selectFirst(finderProperty).attr("href");
            if (!link.contains("http"))
                link = PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())).concat(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return link;
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
        String author = null;
        try {
            String finderProperty = isHome
                    ? PropertiesFactory.getProperty(name.concat(HomeProperties.AUTHOR.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.AUTHOR.toString()));
            Element authorElement = element.selectFirst(finderProperty);
            author = authorElement.text();
        } catch (NullPointerException e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        return author;
    }

    /**
     * retrieve the image url that is in the element attribute
     *
     * @return location of image as a string
     */
    @Override
    protected String renderImage() {
        String imageLocation = null;
        String finderProperty = null;
        Element imageElement = null;
        try {
            finderProperty = isHome ? PropertiesFactory.getProperty(name.concat(HomeProperties.IMAGE.toString()))
                    : PropertiesFactory.getProperty(name.concat(RubricProperties.IMAGE.toString()));
            imageElement = element.selectFirst(finderProperty);
            imageLocation = imageElement.attr("src");
            if (!imageLocation.isEmpty())
                return imageLocation;
        } catch (NullPointerException e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        try {
            imageElement = element.selectFirst(finderProperty);
            imageLocation = imageElement.attr("style");
            imageLocation = imageLocation.substring(22, imageLocation.length() - 3);
        } catch (NullPointerException e) {
            // finderProperty isn't correct
            // e.printStackTrace();
        }
        return imageLocation;
    }

}
