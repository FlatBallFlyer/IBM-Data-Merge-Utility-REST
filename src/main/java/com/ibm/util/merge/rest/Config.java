package com.ibm.util.merge.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.exception.MergeException;


/**
 * Config Servlet (Get Config is the only supported operation)
 */
@WebServlet("/Config")
public class Config extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find the template cache
		Cache cache = (Cache) request.getServletContext().getAttribute("Cache");

		// Get the Config Data
		String config = "";
		try {
			config = cache.getConfig().get();
		} catch (MergeException e) {
			LOGGER.log(Level.SEVERE, "Cache not Configured!");
		}
		
		// Return the Config Data
		response.setContentType("applicaiton/json");
		response.getWriter().write(config);
	}
}
