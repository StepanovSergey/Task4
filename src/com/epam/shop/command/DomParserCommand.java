package com.epam.shop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.shop.model.Category;
import com.epam.shop.parser.MyDomParser;
import com.epam.shop.resource.Constants;

/**
 * This class provides DOM parser command
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
final class DomParserCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(DomParserCommand.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.epam.shop.command.ICommand#execute(java.lang.String)
     */
    @Override
    public String execute(HttpServletRequest request) {
	String page = Constants.ERROR_PAGE;
	String realPathToXml = CommandFactory.getXmlRealPath();
	List<Category> categoryList = null;
	MyDomParser domParser = MyDomParser.getInstance();
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
