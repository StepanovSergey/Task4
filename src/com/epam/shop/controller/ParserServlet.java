package com.epam.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.shop.command.CommandFactory;

/**
 * This class provides servlet description
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class ParserServlet extends HttpServlet {
    /**
     * Generated Serial ID
     */
    private static final long serialVersionUID = 5748929789425383034L;

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    private void process(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	String page = CommandFactory.getCommand(request).execute(request);
	response.sendRedirect(page);
    }
}
