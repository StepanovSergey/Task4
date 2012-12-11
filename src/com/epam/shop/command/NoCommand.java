package com.epam.shop.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.shop.resource.Constants;

/**
 * This class provides command when there is no command in request
 * @author Siarhei_Stsiapanau
 *
 */
final class NoCommand implements ICommand {

    /* (non-Javadoc)
     * @see com.epam.shop.command.ICommand#execute(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String execute(HttpServletRequest request) {
	return Constants.MAIN_PAGE;
    }

}
