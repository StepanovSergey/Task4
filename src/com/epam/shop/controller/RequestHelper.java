package com.epam.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.shop.model.Category;
import com.epam.shop.parser.MySaxParser;
import com.epam.shop.parser.MyStaxParser;

/**
 * This class is servlet requestHelper. It's parse XML file with selected on
 * internet page parser.
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class RequestHelper {
    private static RequestHelper requestHelper;
    public static final String PARSER_PARAMETER = "parser";
    public static final String saxParser = "sax";
    public static final String staxParser = "stax";
    public static final String domParser = "dom";
    public static final String XML_NAME = "products.xml";
    public static final String MAIN_PAGE = "jsp/main.jsp";
    public static final String PRODUCTS_PAGE = "jsp/products.jsp";

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
	System.out.println("Parser=" + parser);
	if (parser == null) {
	    return MAIN_PAGE;
	}
	List<Category> categoryList = new ArrayList<Category>();
	String XMLPath = "\\src\\com\\epam\\shop\\products.xml";//request.getServletContext().getRealPath(XML_NAME);
	if (saxParser.equals(parser)) {
	    MySaxParser saxParser = new MySaxParser();
	    categoryList = saxParser.parse(XMLPath);
	}
	if (staxParser.equals(parser)) {
	    MyStaxParser saxParser = new MyStaxParser();
	    categoryList = saxParser.parse(XMLPath);
	}
	request.getSession().setAttribute("categoryList", categoryList);
	return PRODUCTS_PAGE;
    }
}
