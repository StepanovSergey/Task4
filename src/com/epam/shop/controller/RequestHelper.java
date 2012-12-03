package com.epam.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.shop.command.Constants;
import com.epam.shop.command.DomParserCommand;
import com.epam.shop.command.ICommand;
import com.epam.shop.command.NoCommand;
import com.epam.shop.command.SaxParserCommand;
import com.epam.shop.command.StaxParserCommand;
import com.epam.shop.command.ToMainPageCommand;

/**
 * This class is servlet requestHelper. It's parse XML file with selected on
 * internet page parser.
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class RequestHelper {
    private static Logger logger = Logger.getLogger(RequestHelper.class);
    private static RequestHelper requestHelper;
    private static Map<String, ICommand> commands = new HashMap<>();

    private RequestHelper() {
	commands.put(Constants.SAX_PARSER_COMMAND, new SaxParserCommand());
	commands.put(Constants.STAX_PARSER_COMMAND, new StaxParserCommand());
	commands.put(Constants.DOM_PARSER_COMMAND, new DomParserCommand());
	commands.put(Constants.TO_MAIN_PAGE_COMMAND, new ToMainPageCommand());
	commands.put(Constants.NO_COMMAND, new NoCommand());
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
	String commandString = request
		.getParameter(Constants.COMMAND_PARAMETER);
	if (logger.isDebugEnabled()){
	    logger.debug("Current command: " + commandString);
	}
	ICommand command = commands.get(commandString);
	if (command == null){
	    command = commands.get(Constants.NO_COMMAND);
	}
	String page = command.execute(request);
	if (logger.isDebugEnabled()){
	    logger.debug("Page to go: " + page);
	}
	return page;
    }
}
