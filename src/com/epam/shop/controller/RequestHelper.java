package com.epam.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.shop.model.Category;
import com.epam.shop.parser.MyDomParser;
import com.epam.shop.parser.MySaxParser;
import com.epam.shop.parser.MyStaxParser;

/**
 * This class is servlet requestHelper. It's parse XML file with selected on
 * internet page parser.
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class RequestHelper {
    private static RequestHelper requestHelper;
    public static final String PARSER_PARAMETER = "parser";
    public static final String SAX_PARSER = "sax";
    public static final String STAX_PARSER = "stax";
    public static final String DOM_PARSER = "dom";
    public static final String XML_NAME = "WEB-INF\\classes\\products.xml";
    public static final String MAIN_PAGE = "jsp/main.jsp";
    public static final String PRODUCTS_PAGE = "jsp/products.jsp";
    public static final String ERROR_PAGE = "jsp/error.jsp";

    private RequestHelper() {
    }

    /**
     * @return the requestHelper
     */
    public static RequestHelper getRequestHelper() {
	if (requestHelper == null) {
	    requestHelper = new RequestHelper();
	}
	return requestHelper;
    }

    public String execute(HttpServletRequest request) {
	String parser = request.getParameter(PARSER_PARAMETER);
	if (parser == null) {
	    return MAIN_PAGE;
	}
	List<Category> categoryList = new ArrayList<Category>();
	String XMLPath = request.getServletContext().getRealPath(XML_NAME);
	if (SAX_PARSER.equals(parser)) {
	    MySaxParser saxParser = new MySaxParser();
	    categoryList = saxParser.parse(XMLPath);
	} else if (STAX_PARSER.equals(parser)) {
	    MyStaxParser saxParser = new MyStaxParser();
	    categoryList = saxParser.parse(XMLPath);
	} else if (DOM_PARSER.equals(parser)) {
	    MyDomParser domParser = new MyDomParser();
	    categoryList = domParser.parse(XMLPath);
	} else {
	    return ERROR_PAGE;
	}
	if (categoryList == null) {
	    return ERROR_PAGE;
	}
	request.getSession().setAttribute("categoryList", categoryList);
	return PRODUCTS_PAGE;
    }
}
