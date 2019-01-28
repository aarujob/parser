package com.aarujob.parseurhtml.parsers;

import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.parsers.igfm.IgfmParser;
import com.aarujob.parseurhtml.parsers.leral.LeralParser;
import com.aarujob.parseurhtml.parsers.senego.SenegoParser;
import com.aarujob.parseurhtml.parsers.seneweb.SenewebParser;
import com.aarujob.parseurhtml.parsers.walfnet.WalfnetParser;
import com.aarujob.parseurhtml.parsers.xibar.XibarParser;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import java.util.Properties;

/**
 * provides the parser of an information page.
 *
 * @author serignediaby
 * @version 1.0.0
 */
public class ParserFactory {

    private final static Properties PROPERTIES = PropertiesFactory.getProperties();
    public final static String IGFM_NAME = "igfm";
    public final static String SENEWEB_NAME = "seneweb";
    public final static String SENEGO_NAME = "senego";
    public final static String WALF_NAME = "walfnet";
    public final static String XIBAR_NAME = "xibar";
    public final static String LERAL_NAME = "leral";
    /**
     * provide a parser for home site
     *
     * @param name        the name of site
     * @param urlProperty the url property of the site that will be parsed
     * @return an parser instance depending on given parameters
     */
    public static BaseParser getParser(String name) {

        if (name.equals(IGFM_NAME)) {
            return new IgfmParser(PROPERTIES.getProperty(IGFM_NAME.concat(HomeProperties.URL.toString())));
        }
        if (name.equals(SENEWEB_NAME)) {
            return new SenewebParser(PROPERTIES.getProperty(SENEWEB_NAME.concat(HomeProperties.URL.toString())));
        }
        if (name.equals(SENEGO_NAME)) {
            return new SenegoParser(PROPERTIES.getProperty(SENEGO_NAME.concat(HomeProperties.URL.toString())));
        }
        if (name.equals(WALF_NAME)) {
            return new WalfnetParser(PROPERTIES.getProperty(WALF_NAME.concat(HomeProperties.URL.toString())));
        }
        if (name.equals(XIBAR_NAME)) {
            return new XibarParser(PROPERTIES.getProperty(XIBAR_NAME.concat(HomeProperties.URL.toString())));
        }
        if (name.equals(LERAL_NAME)) {
            return new LeralParser(PROPERTIES.getProperty(LERAL_NAME.concat(HomeProperties.URL.toString())));
        }
        return null;
    }

    /**
     * provide a parser for a specific category of a site
     *
     * @param name
     * @param category
     * @return an parser instance depending on the name and the category
     */
    public static BaseParser getParser(String name, CategoryModel category) {

        if (name.equals(IGFM_NAME)) {
            return new IgfmParser(category);
        }
        if (name.equals(SENEWEB_NAME)) {
            return new SenewebParser(category);
        }
        if (name.equals(SENEGO_NAME)) {
            return new SenegoParser(category);
        }
        if (name.equals(WALF_NAME)) {
            return new WalfnetParser(category);
        }
        if (name.equals(XIBAR_NAME)) {
            return new XibarParser(category);
        }
        if (name.equals(LERAL_NAME)) {
            return new LeralParser(category);
        }
        return null;
    }
}
