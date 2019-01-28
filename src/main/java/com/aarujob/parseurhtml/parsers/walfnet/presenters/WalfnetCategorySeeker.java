package com.aarujob.parseurhtml.parsers.walfnet.presenters;

import com.aarujob.parseurhtml.presenters.CategorySeeker;
import org.jsoup.nodes.Element;

/**
 * retrieves all categories in an element
 *
 * @author smag
 * @version 1.0.0
 */
public class WalfnetCategorySeeker extends CategorySeeker {

    /**
     * <b>Constructor</b>
     *
     * @param element references element html retrieved in Walfnet website
     * @param name    the name of website
     */
    public WalfnetCategorySeeker(Element element, String name) {
        super(element, name);
    }

}
