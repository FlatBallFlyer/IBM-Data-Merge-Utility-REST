package com.ibm.util.merge.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.exception.MergeException;


/**
 * Servlet implementation class Rest
 */
public class Template extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Template.class.getName());
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
			String fullname = request.getPathInfo();
			if (fullname == null) fullname = "/";
			fullname = fullname.substring(1);
			String reply = cache.getTemplate(fullname);
			response.setContentType("text/json");
			response.getWriter().write(reply);
		} catch (Throwable t) {
			LOGGER.log(Level.WARNING, t.getMessage());
			response.getWriter().write(t.getMessage());
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
			String theGroup = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			String reply = cache.putTemplate(theGroup);
			response.setContentType("text/json");
			response.getWriter().write(reply);
		} catch (MergeException e) {
			LOGGER.log(Level.WARNING, e.getErrorMessage());
			response.getWriter().write(e.getErrorMessage());
		} catch (Throwable t) {
			LOGGER.log(Level.WARNING, t.getMessage());
			response.getWriter().write(t.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
			String theGroup = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			String reply = cache.postTemplate(theGroup);
			response.setContentType("text/json");
			response.getWriter().write(reply);
		} catch (MergeException e) {
			LOGGER.log(Level.WARNING, e.getErrorMessage());
			response.getWriter().write(e.getErrorMessage());
		} catch (Throwable t) {
			LOGGER.log(Level.WARNING, t.getMessage());
			response.getWriter().write(t.getMessage());
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
			String fullname = request.getPathInfo();
			if (fullname == null) fullname = "/";
			fullname = fullname.substring(1);
			String reply = cache.deleteTemplate(fullname);
			response.setContentType("text/json");
			response.getWriter().write(reply);
		} catch (MergeException e) {
			LOGGER.log(Level.WARNING, e.getErrorMessage());
			response.getWriter().write(e.getErrorMessage());
		} catch (Throwable t) {
			LOGGER.log(Level.WARNING, t.getMessage());
			response.getWriter().write(t.getMessage());
		}
	}
}
