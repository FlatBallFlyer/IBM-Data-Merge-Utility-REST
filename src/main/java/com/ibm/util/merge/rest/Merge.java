package com.ibm.util.merge.rest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.Merger;
import com.ibm.util.merge.exception.MergeException;
import com.ibm.util.merge.template.Template;


/**
 * Servlet implementation class Rest
 */
public class Merge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Merge.class.getName());
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Merger merger = null;
		try {
			merger = new Merger(
					(Cache) request.getServletContext().getAttribute("Cache"), 
					request.getPathInfo().substring(1), 
					request.getParameterMap(), 
					request.getInputStream(), 
					request.getCharacterEncoding()
				);
			Template merged = merger.merge();
			response.setContentType(merged.getContentType());
			response.setHeader("Content-Disposition", merged.getContentDisposition());
			LOGGER.log(Level.SEVERE, "mergeReturn:" + merged.getMergeReturn());
			switch (merged.getMergeReturn()) {
				case Template.RETURN_CONTENT :
					merged.getMergedOutput().streamValue(response.getOutputStream());
					break;
				case Template.RETURN_ARCHIVE :
		    			File archive = merger.getArchive().getArchiveFile();
		    		    FileUtils.copyFile(archive, response.getOutputStream());
		    		    archive.delete();
		    		    break;
				case Template.RETURN_FORWARD :
					// todo Forward request to merged.getContentRedirectUrl()
			}			
		} catch (MergeException e) {
			LOGGER.log(Level.WARNING, "MergeException:" + e.getErrorMessage());
			response.getWriter().write(e.getErrorMessage());
		} catch (Throwable t) {
			LOGGER.log(Level.WARNING, "Throwable:" + t.getMessage());
			response.getWriter().write("Throwable Caught!");
		}
	}
}
