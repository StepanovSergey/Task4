package com.epam.shop.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.shop.resource.Constants;

/**
 * This command provides redirect to main page
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
final class ToMainPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
	return Constants.MAIN_PAGE;
    }

}
