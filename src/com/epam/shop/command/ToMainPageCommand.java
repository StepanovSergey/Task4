package com.epam.shop.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This command provides redirect to main page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public class ToMainPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
	return Constants.MAIN_PAGE;
    }

}
