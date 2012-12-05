package com.epam.shop.resource;

/**
 * This class provides application constants
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class Constants {
    private Constants() {
    }

    // Command constants
    public static final String MAIN_PAGE = "jsp/main.jsp";
    public static final String ERROR_PAGE = "jsp/error.jsp";
    public static final String PRODUCTS_PAGE = "jsp/products.jsp";
    public static final String COMMAND_PARAMETER = "command";
    public static final String SAX_PARSER_COMMAND = "sax_parser";
    public static final String STAX_PARSER_COMMAND = "stax_parser";
    public static final String DOM_PARSER_COMMAND = "dom_parser";
    public static final String TO_MAIN_PAGE_COMMAND = "main_page";
    public static final String NO_COMMAND = "no_command";
    public static final String LOCAL_PATH_TO_XML = "WEB-INF\\classes\\products.xml";
    public static final String CATEGORY_LIST_ATTRIBUTE = "categoryList";
    // Parser constants
    public static final String CATEGORY_TAG = "category";
    public static final String SUBCATEGORY_TAG = "subcategory";
    public static final String PRODUCT_TAG = "product";
    public static final String PRODUCER_TAG = "producer";
    public static final String MODEL_TAG = "model";
    public static final String DATE_OF_ISSUE_TAG = "date_of_issue";
    public static final String PRICE_TAG = "price";
    public static final String COLOR_TAG = "color";
    public static final String NOT_IN_STOCK_TAG = "not_in_stock";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String NAME_ATTRIBUTE = "name";
}
