package com.ibm.util.merge.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
		LOGGER.log(Level.SEVERE, "doGet");
		mergeIt(request, response);
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.log(Level.SEVERE, "doPut");
		mergeIt(request, response);
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.log(Level.SEVERE, "doPost");
		
		// This foo is a hack, I'm not sure why but the Merger constructor can not read the input stream
		// in the same way as the get and put input stream, without this statement the input read is an empty string. 
		@SuppressWarnings("unused")
		String foo = request.getInputStream().toString();
		
		mergeIt(request, response);
	}
	
	/**
	 * Perform the Merge
	 */
	private void mergeIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Merger merger = null;
		OutputStream output = response.getOutputStream();
		try {
			merger = new Merger(
					(Cache) request.getServletContext().getAttribute("Cache"), 
					request.getPathInfo().substring(1), 
					request.getParameterMap(), 
					request.getInputStream(), 
					request.getCharacterEncoding()
				);
			LOGGER.log(Level.SEVERE, "Start");
			Template merged = merger.merge();
			response.setContentType(merged.getContentType());
			response.setHeader("Content-Disposition", merged.getContentDisposition());
			LOGGER.log(Level.SEVERE, "Finish mergeReturn:" + merged.getMergeReturn());
			switch (merged.getMergeReturn()) {
				case Template.RETURN_CONTENT :
					merged.getMergedOutput().streamValue(output);
					break;
				case Template.RETURN_ARCHIVE :
		    			File archive = merger.getArchive().getArchiveFile();
		    		    FileUtils.copyFile(archive, output);
		    		    archive.delete();
		    		    break;
				case Template.RETURN_FORWARD :
					// todo Forward request to merged.getContentRedirectUrl()
			}			
		} catch (MergeException e) {
			LOGGER.log(Level.SEVERE, "MergeException:" + e.getErrorMessage());
			Writer writer = new OutputStreamWriter(output, "UTF-8");
			writer.write(e.getErrorMessage());
		} catch (Throwable t) {
			LOGGER.log(Level.SEVERE, "Throwable:" + t.getMessage());
			Writer writer = new OutputStreamWriter(output, "UTF-8");
			writer.write("Throwable Caught!");
		}
	}
}
