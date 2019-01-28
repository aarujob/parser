package com.aarujob.parseurhtml.parsers.seneweb.presenters;

import com.aarujob.parseurhtml.presenters.CategorySeeker;
import org.jsoup.nodes.Element;

/**
 * retrieves all categories in an element
 *
 * @author smag
 * @version 1.0.0
 */
public class SenewebCategorySeeker extends CategorySeeker {

    /**
     * <b>Constructor</b>
     *
     * @param element references element html retrieved in seneweb website
     * @param name the name of website
     */
    public SenewebCategorySeeker(Element element, String name) {
        super(element, name);
    }

}
