package com.epam.shop.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.shop.resource.Constants;

/**
 * This class provides command factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private static Map<String, ICommand> commands;
    private static String XML_REAL_PATH;

    private CommandFactory() {
	commands = new HashMap<String, ICommand>();
	commands.put(Constants.SAX_PARSER_COMMAND, new SaxParserCommand());
	commands.put(Constants.STAX_PARSER_COMMAND, new StaxParserCommand());
	commands.put(Constants.DOM_PARSER_COMMAND, new DomParserCommand());
	commands.put(Constants.TO_MAIN_PAGE_COMMAND, new ToMainPageCommand());
	commands.put(Constants.NO_COMMAND, new NoCommand());
    }

    /**
     * @return the instance
     */
    public static CommandFactory getInstance() {
	return instance;
    }

    public static ICommand getCommand(HttpServletRequest request) {
	if (XML_REAL_PATH == null) {
	    XML_REAL_PATH = request.getServletContext().getRealPath(
		    Constants.LOCAL_PATH_TO_XML);
	}
	String commandName = request.getParameter(Constants.COMMAND_PARAMETER);
	ICommand command = commands.get(commandName);
	if (command == null) {
	    command = commands.get(Constants.NO_COMMAND);
	}
	return command;
    }

    /**
     * @return the XML_REAL_PATH
     */
    public static String getXmlRealPath() {
        return XML_REAL_PATH;
    }

}
