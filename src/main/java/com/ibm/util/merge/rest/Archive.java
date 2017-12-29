package com.ibm.util.merge.rest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ibm.util.merge.Cache;

public class Archive extends HttpServlet {
	private static final long serialVersionUID = -1L;
	private static final Logger LOGGER = Logger.getLogger(Archive.class.getName());

	public Archive() {
		super();
	}

    /**
     * Get and remove the archive
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    		try {
    			Cache cache = (Cache) request.getServletContext().getAttribute("Cache");
    			String fullname = request.getPathInfo().substring(1);
    			File archive = new File(cache.getConfig().getTempFolder() + "/" + fullname);
    			response.setContentType("application/octet-stream");
    			response.setHeader("Content-Disposition", "filename=\"" + fullname + "\"");
    			LOGGER.log(Level.WARNING, "Get Archive " + archive.getAbsolutePath());
    		    FileUtils.copyFile(archive, response.getOutputStream());
    		    archive.delete();
    		} catch (Throwable e) {
    			response.getWriter().write("Error");
    			LOGGER.log(Level.WARNING, "Get Archive Throwable Error" + e.getMessage());
    		}
    }
    
}
