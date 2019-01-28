package com.aarujob.parseurhtml.exceptions;

import org.jsoup.select.Selector.SelectorParseException;

/**
 * CategorySeekerException
 * @author serignediaby
 */
public class CategorySeekerException extends SelectorParseException {


    private static final long serialVersionUID = 1L;

    public CategorySeekerException(String message) {
        super(message);
    }

}