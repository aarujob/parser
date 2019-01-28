package com.aarujob.parseurhtml.parsers.seneweb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.helpers.FakeParser;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebCategoryRenderer;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebCategorySeeker;
import com.aarujob.parseurhtml.properties.HomeProperties;
import com.aarujob.parseurhtml.properties.PropertiesFactory;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * @author smag
 */
public class SenewebCategoryPresenterTest {

    private FakeParser fakeParser;
    private String name;
    private String firstCategory;

    @Before
    public void setUp() {
        name = "seneweb";
        firstCategory = "firstCategory";
        fakeParser = new FakeParser(PropertiesFactory.getProperty(name.concat(HomeProperties.URL.toString())), name);
    }

    @Test
    public void seekCategoriesTest() {
        assertNotNull(fakeParser); // - Property File Load Passed - Connection to Specified URL Successful
        SenewebCategorySeeker senewebCategorySeeker = new SenewebCategorySeeker(fakeParser.getBody(),name);
        Elements elements = senewebCategorySeeker.seekCategories();
        assertNotNull(elements); // Category recovery properties are correct
        assertEquals(elements.size(), 9); // Site seneweb contains exactly nine (9) categories
    }

    @Test
    public void renderCategoryTest() {
        SenewebCategoryRenderer senewebCategoryRenderer = new SenewebCategoryRenderer(
                fakeParser.getFakeElement(firstCategory),name);
        CategoryModel category = senewebCategoryRenderer.renderCategory();
        assertNotNull(category); // formatting a rubric in validated category successful
        assertEquals(category.getName(), fakeParser.getFakeCategory().getName()); // the correct value of category name
                                                                                  // are retrived
        assertEquals(category.getLink(), fakeParser.getFakeCategory().getLink()); // the correct value of category link
                                                                                  // is retrieved
    }
}