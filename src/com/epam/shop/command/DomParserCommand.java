package com.epam.shop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.shop.model.Category;
import com.epam.shop.parser.MyDomParser;

/**
 * This class provides DOM parser command
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class DomParserCommand implements ICommand {
    private static Logger logger = Logger.getLogger(DomParserCommand.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.epam.shop.command.ICommand#execute(java.lang.String)
     */
    @Override
    public String execute(HttpServletRequest request) {
	String page = Constants.ERROR_PAGE;
	String realPathToXml = request.getServletContext().getRealPath(
		Constants.LOCAL_PATH_TO_XML);
	List<Category> categoryList = null;
	MyDomParser domParser = new MyDomParser();
	categoryList = domParser.parse(realPathToXml);
	if (categoryList == null) {
	    if (logger.isDebugEnabled()) {
		logger.debug("List of categories is null");
	    }
	    return page;
	}
	request.getSession().setAttribute(Constants.CATEGORY_LIST_ATTRIBUTE,
		categoryList);
	page = Constants.PRODUCTS_PAGE;
	return page;
    }

}
