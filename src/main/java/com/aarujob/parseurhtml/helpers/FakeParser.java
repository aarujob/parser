package com.aarujob.parseurhtml.helpers;

import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.aarujob.models.CategoryModel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author serignediaby
 * @version 1.0.0
 */
public class FakeParser {

    private static final String SEPARATOR = String.valueOf(File.separatorChar);
    private Connection connection;
    private String url;
    private String name;
    private Document document;
    private Element head;
    private Element body;

    public FakeParser(String url, String name) {
        this.url = url;
        this.name = name;
        initializer(url);
    }

    /**
     *
     * @param url
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

    private Object readValues() {
        FileReader reader = null;
        try {
            File file = new File(getClass().getResource("/files/values.json").getFile());
            reader = new FileReader(file);
            return new JSONParser().parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Element getHead() {
        return this.head;
    }

    public void setHead(Element head) {
        this.head = head;
    }

    public Element getBody() {
        return this.body;
    }

    public void setBody(Element body) {
        this.body = body;
    }

    /**
     * Return a value for a given attribute by reading the values.json file
     * 
     * @param jsonAttribute
     * @return
     */
    public String jsonReader(String jsonAttribute) {
        JSONObject jsonObject = (JSONObject) readValues();
        return (String) ((JSONObject) jsonObject.get(this.name)).get(jsonAttribute);
    }

    /**
     * Return a fake Element (category of article ) for a specific website depending
     * on the given jsonAttribute
     * 
     * @param jsonAttribute
     * @return
     */
    public Element getFakeElement(String jsonAttribute) {
        return Jsoup.parse(jsonReader(jsonAttribute), "", Parser.htmlParser()).body().child(0);
    }

    /**
     * Return a fake category Object for the purpose of test the category rendering
     * 
     * @return
     */
    public CategoryModel getFakeCategory() {
        CategoryModel category = new CategoryModel();
        category.setName(jsonReader("categoryName"));
        category.setLink(jsonReader("categoryLink"));
        return category;

    }
}
