package com.aarujob.parseurhtml.parsers.igfm.presenters;

import static org.junit.Assert.assertNotNull;

import com.aarujob.parseurhtml.helpers.FakeParser;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;

import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author serignediaby
 */
public class IgfmArticlePresenterTest {

    private FakeParser fakeParser;
    private boolean isHome;
    private String name;
    private String firstCategory;
    private String lastCategory;

    @Before
    public void setUp() {
        name = "igfm";
        firstCategory = "firstCategory";
        lastCategory = "lastCategory";
    }

    @After
    public void TearDown() {
        fakeParser = null;
    }

    @Test
    public void seekArticlesHomeTest() {
        isHome = true;
        fakeParser = new FakeParser(PropertiesFactory.getProperty(name + HomeProperties.URL), name);
        IgfmArticleSeeker articleSeeker = new IgfmArticleSeeker(fakeParser.getBody(), isHome, name);
        Elements articles = articleSeeker.seekArticles();
        assertNotNull(articles);
    }

    @Test
    public void seekArticlesRubricTest() {
        isHome = false;
        String rubricUrl = "https://www.igfm.sn/category/actualite";
        fakeParser = new FakeParser(rubricUrl, name);
        IgfmArticleSeeker articleSeeker = new IgfmArticleSeeker(fakeParser.getBody(), isHome, name);
        Elements articles = articleSeeker.seekArticles();
        assertNotNull(articles);
    }

    @Test
    public void renderArticlesTest() {

    }
}