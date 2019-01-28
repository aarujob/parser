package com.aarujob.parseurhtml.parsers.igfm.presenters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.helpers.FakeParser;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author serignediaby
 */
public class IgfmCategoryPresenterTest {

    private FakeParser fakeParser;
    private String name;
    private String firstCategory;
    private String lastCategory;

    /**
     * set up the needed variables for be able to run the tests, called before each
     * test method initialize the name of the site to parse initiliaze a fakeParser
     * object for the purpose f tests
     */
    @Before
    public void setUp() {
        name = "igfm";
        firstCategory = "firstCategory";
        lastCategory = "lastCategory";
        fakeParser = new FakeParser(PropertiesFactory.getProperty(name + HomeProperties.URL), name);
    }

    /**
     * Teardown the tests environement, called after each test method
     */
    @After
    public void TearDown() {
    }

    /**
     * for test the seekCategories method of IgfmCategorySeeker
     */
    @Test
    public void seekCategoriesTest() {
        IgfmCategorySeeker igfmCategorySeeker = new IgfmCategorySeeker(fakeParser.getBody(), name);
        Elements elements = igfmCategorySeeker.seekCategories();
        Element firstElement = elements.first();
        Element lastElement = elements.last();
        assertNotNull(elements);
        assertEquals(elements.size(), 8);
        // TODO: try to make works this test
        // assertEquals(firstElement, fakeParser.getFakeElement(firstCategory));
        // assertEquals(lastElement.toString(),fakeParser.getFakeElement(lastCategory));
    }

    /**
     * for test the renderCategory method of IgfmCategoryRenderer assertNotNull
     * assertEquals(Object expected, Object actual)
     * 
     */
    @Test
    public void renderCategoryTest() {
        IgfmCategoryRenderer igfmCategoryRenderer = new IgfmCategoryRenderer(fakeParser.getFakeElement(firstCategory), name);
        CategoryModel category = igfmCategoryRenderer.renderCategory();
        assertNotNull(category);
        assertEquals(category.getName(), fakeParser.getFakeCategory().getName());
        assertEquals(category.getLink(), fakeParser.getFakeCategory().getLink());
    }
}