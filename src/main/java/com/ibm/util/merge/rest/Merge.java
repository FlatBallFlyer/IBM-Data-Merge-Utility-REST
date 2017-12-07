package com.ibm.util.merge.rest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.Merger;
import com.ibm.util.merge.exception.MergeException;
import com.ibm.util.merge.template.Template;


/**
 * Servlet implementation class Rest
 */
@WebServlet("/Merge")
public class Merge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cache cache;
	
    /**
     * Default constructor. 
     */
    public Merge() {
        try {
			cache = new Cache();
		} catch (MergeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Merger merger = null;
		String template = ""; // TODO - template from request.parts
		String payload = IOUtils.toString(request.getInputStream(), "ISO-8859-1");
		try {
			merger = new Merger(cache, 
					template, 
					request.getParameterMap(), 
					payload
				);
			Template merged = merger.merge();
			merged.getMergedOutput().streamValue(response.getOutputStream());
		} catch (MergeException e) {
			// TODO handle exception using e.getErrorMessage(merger);
		}
	}

}
