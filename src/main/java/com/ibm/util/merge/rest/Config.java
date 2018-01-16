package com.ibm.util.merge.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.exception.MergeException;


/**
 * Config Servlet (Get Config is the only supported operation)
 */
public class Config extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
			response.setContentType("text/json");
			response.getWriter().write(cache.getConfig().get());
		} catch (MergeException e) {
			LOGGER.log(Level.SEVERE, "Cache not Configured!");
		}
		
	}
}
