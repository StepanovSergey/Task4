package com.epam.shop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.shop.model.Category;
import com.epam.shop.parser.MySaxParser;

/**
 * This class provides SAX parser command
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class SaxParserCommand implements ICommand {
    private static Logger logger = Logger.getLogger(SaxParserCommand.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.shop.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     * )
     */
    @Override
    public String execute(HttpServletRequest request) {
	String page = Constants.ERROR_PAGE;
	String realPathToXml = request.getServletContext().getRealPath(
		Constants.LOCAL_PATH_TO_XML);
	List<Category> categoryList = null;
	if (logger.isDebugEnabled()){
	    logger.debug("Processing SAX parser");
	}
	MySaxParser saxParser = new MySaxParser();
	categoryList = saxParser.parse(realPathToXml);
	if (categoryList == null) {
	    if (logger.isDebugEnabled()) {
		logger.debug("List of categories is null");
	    }
	    return page;
	}
	request.getSession().setAttribute(Constants.CATEGORY_LIST_ATTRIBUTE, categoryList);
	page = Constants.PRODUCTS_PAGE;
	return page;
    }

}
