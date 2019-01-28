/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aarujob.parseurhtml.parsers.igfm;

import com.aarujob.models.ArticleModel;
import com.aarujob.models.CategoryModel;
import com.aarujob.parseurhtml.parsers.BaseParser;
import com.aarujob.parseurhtml.parsers.igfm.presenters.IgfmArticleRenderer;
import com.aarujob.parseurhtml.parsers.igfm.presenters.IgfmArticleSeeker;
import com.aarujob.parseurhtml.parsers.igfm.presenters.IgfmCategoryRenderer;
import com.aarujob.parseurhtml.parsers.igfm.presenters.IgfmCategorySeeker;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;

/**
 *
 * @author serignediaby
 */
public class IgfmParser extends BaseParser {

    private CategoryModel category;
    private static final String NAME = "igfm";

    /**
     * Constructor to call will parsing the home page
     * 
     * @param url
     */
    public IgfmParser(String url) {
        super(url);
    }

    /**
     * Constructor to call will parsing a rubric page
     * 
     * @param url
     * @param category
     */
    public IgfmParser(CategoryModel category) {
        super(category.getLink());
        this.category = category;
    }

    /**
     * Find the Comments at the inherited class method declaration
     * 
     * @param element
     * @return
     */
    @Override
    protected CategoryModel extractOneCategory(Element element) {
        CategoryModel category = null;
        try {
            IgfmCategoryRenderer categoryRenderer = new IgfmCategoryRenderer(element,NAME);
            category = categoryRenderer.renderCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * Find the Comments at the inherited class method declaration
     * 
     * @return
     */
    @Override
    public List<CategoryModel> getCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        try {
            IgfmCategorySeeker categorySeeker = new IgfmCategorySeeker(getBody(),NAME);
            categorySeeker.seekCategories().forEach(category -> {
                categories.add(extractOneCategory(category));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Find the Comments at the inherited class method declaration
     * 
     * @param articleElement
     * @return
     */
    @Override
    protected ArticleModel extractOneArticle(Element articleElement) {
        ArticleModel article = null;
        boolean isHome = category == null;
        try {
            IgfmArticleRenderer articleRenderer = new IgfmArticleRenderer(articleElement,isHome,NAME);
            article = articleRenderer.renderArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * Find the Comments at the inherited class method declaration
     * 
     * @return
     */
    @Override
    public List<ArticleModel> getArticles() {
        List<ArticleModel> articles = new ArrayList<>();
        boolean isHome = category == null;
        IgfmArticleSeeker articleSeeker = new IgfmArticleSeeker(getBody(), isHome, NAME);
        articleSeeker.seekArticles().forEach(element -> {
            ArticleModel article = extractOneArticle(element);
            if (!isHome)
                article.setCategoryModel(category);
            articles.add(article);
        });
        return articles;
    }

}
