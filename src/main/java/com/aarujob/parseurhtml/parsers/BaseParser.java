package com.aarujob.parseurhtml.parsers;

import com.aarujob.models.ArticleModel;
import com.aarujob.models.CategoryModel;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * represents the base of the html page parser of this project. It
 * <b>connects</b> to a web page, then <b>retrieve</b> the content of the page.
 * Finally, it <b>identifies</b> the different parts (document, head, body ...)
 * according to the html structuring
 *
 * @author smag
 * @version 1.0.0
 */
public abstract class BaseParser {

    private Connection connection;
    private String url;
    private Document document;
    private Element head;
    private Element body;

    /**
     * <b>Constructor</b>
     * initialize all the parameters needed for parsing seneweb web page when it's called
     *
     * @param url homepage link of website 
     */
    public BaseParser(String url) {
        this.url = url;
        this.initializer(url);
    }

    /**
     * <b>Constructor</b>
     * initialize all the parameters needed for parsing web page when it's called
     *
     * @param url specific page link of website 
     */
    private void initializer(String url) {
        try {
            connection = Jsoup.connect(url);
            document = connection.get();
            head = document.head();
            body = document.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extract a Category Object when you pass it an html element 
     *
     * @param element
     * @return CategoryModel which contains name and url
     */
    protected abstract CategoryModel extractOneCategory(Element element);

    /**
     * get all Categories inside a given page of website to parse
     *
     * @return a list of CategoryModel which contains name and url
     */
    public abstract List<CategoryModel> getCategories();

    /**
     * Extract an ArticleModel when ou pass it a html element
     *
     * @return an ArticleModel object correspoding to html element
     */
    protected abstract ArticleModel extractOneArticle(Element articleElement);

    /**
     * get Articles inside a given page
     *
     * @return the list of article inside a given page
     */
    public abstract List<ArticleModel> getArticles();

    /**
     * get the value of attribute connection
     *
     * @return an object Connection corresponding the value of url
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Change the value of attribute conncetion
     *
     * @param connection the new value connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * get the value of attribute
     *
     * @return the value of the article url as a string
     */
    public String getUrl() {
        return url;
    }

    /**
     * change the value of the url
     *
     * @param url the new value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDocument() {
        return document;
    }

    /**
     * change the value of the document
     *
     * @param document the new value of document
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * get the value of attribute head
     *
     * @return the value of head element
     */
    public Element getHead() {
        return head;
    }

    /**
     * change the value of the head
     *
     * @param head the new value of head
     */
    public void setHead(Element head) {
        this.head = head;
    }

    /**
     * get the value of attribute body
     *
     * @return the value of body element
     */
    public Element getBody() {
        return body;
    }

    /**
     * change the value of the body
     *
     * @param body the new value of body
     */
    public void setBody(Element body) {
        this.body = body;
    }

}
