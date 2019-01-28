
package com.aarujob.parseurhtml.parsers.seneweb;

import com.aarujob.parseurhtml.helpers.FakeParser;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author smag
 */
public class SenewebArticlePresenterTest {
    FakeParser fakeParserHome = null;
    FakeParser fakeParserRubric = null;
    private String article_home;
    private String name;

    @Before
    public void setup() {
        name = "seneweb";
        article_home = "article_home";
        fakeParserHome = new FakeParser(PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())),
                name);
    }

    @Test
    public void seekArticlestesHome() {

    }

    @Test
    public void seekArticlestesRubric() {
    }

    @Test
    public void rendererArticleHome() {

    }

    @Test
    public void rendererArticleRubric() {

    }
}