package com.aarujob.parseurhtml.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * provides properties to application
 *
 * @author smag
 * @version 1.0.0
 */
public class PropertiesFactory {

    private static Properties prop = new Properties();

    private PropertiesFactory() {
    }

    static {
        InputStream resourceAsStream = null;
        try {
            Class cls = new PropertiesFactory().getClass();
            resourceAsStream = cls.getResourceAsStream("/files/sites.properties");
            prop.load(resourceAsStream);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get the properties file
     *
     * @return Properties object which contains all the properties of project
     */
    public static Properties getProperties() {
        return prop;
    }

    /**
     * get the value corresponding to the key propertyName
     *
     * @param propertyName the key which value will retrieve
     * @return Properties object which contains all the properties of project
     */
    public static String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }
}
