package com.aarujob.parseurhtml.parsers.seneweb;

import com.aarujob.models.ArticleModel;
import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.parsers.BaseParser;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebArticleRenderer;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebArticleSeeker;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebCategoryRenderer;
import com.aarujob.parseurhtml.parsers.seneweb.presenters.SenewebCategorySeeker;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;

/**
 * represents the base of the html page parser of this project. It
 * <b>connects</b> to seneweb web page, then <b>retrieve</b> the content of the
 * page. Finally, it <b>identifies</b> the different parts (document, head, body
 * ...) according to the html structuring
 *
 * @author smag
 * @version 1.0.0
 */
public class SenewebParser extends BaseParser {

    private CategoryModel category;

    private static final String NAME = "seneweb";

    /**
     * <b>Constructor</b> initialize all the parameters needed for parsing seneweb
     * web page when it's called
     *
     * @param url homepage of seneweb website
     */
    public SenewebParser(String url) {
        super(url);
    }

    /**
     * <b>Constructor</b> initialize all the parameters needed for parsing seneweb
     * web page when it's called
     *
     * @param category specific page link of seneweb website
     */
    public SenewebParser(CategoryModel category) {
        super(category.getLink());
        this.category = category;
    }

    /**
     * Extract a Category Object when you pass it an html element and return it at
     * the end
     *
     * @param element
     * @return Category which contains name and url
     */
    @Override
    protected CategoryModel extractOneCategory(Element element) {
        CategoryModel category = null;
        try {
            SenewebCategoryRenderer categoryRenderer = new SenewebCategoryRenderer(element, NAME);
            category = categoryRenderer.renderCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * get all Categories inside a given page of website to parse
     *
     * @return a list of CategoryModel which contains name and url
     */
    @Override
    public List<CategoryModel> getCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        try {
            SenewebCategorySeeker categorySeeker = new SenewebCategorySeeker(getBody(), NAME);
            categorySeeker.seekCategories().forEach(category -> {
                categories.add(extractOneCategory(category));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Extract an ArticleModel when ou pass it a html element
     *
     * @return an ArticleModel object correspoding to <b>articleElement</b>
     */
    @Override
    protected ArticleModel extractOneArticle(Element articleElement) {
        boolean isHome = category == null;
        ArticleModel article = null;
        try {
            SenewebArticleRenderer articleRenderer = new SenewebArticleRenderer(articleElement, isHome, NAME);
            article = articleRenderer.renderArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * get Articles inside a given page
     *
     * @return the list of article inside a given seneweb website
     */
    @Override
    public List<ArticleModel> getArticles() {
        boolean isHome = category == null;
        List<ArticleModel> articles = new ArrayList<>();
        SenewebArticleSeeker articleSeeker = new SenewebArticleSeeker(getBody(), isHome, NAME);
        articleSeeker.seekArticles().forEach(element -> {
            try {
                ArticleModel article = extractOneArticle(element);
                if (!isHome) {
                    article.setCategoryModel(category);
                }
                if (article != null) {
                    articles.add(article);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return articles;
    }
}
