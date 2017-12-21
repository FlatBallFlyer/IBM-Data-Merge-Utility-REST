package com.ibm.util.merge.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.util.merge.Cache;
import com.ibm.util.merge.exception.MergeException;

public class Initialize extends HttpServlet {
	private static final long serialVersionUID = -8057529556616935613L;
	private static final Logger LOGGER = Logger.getLogger(Merge.class.getName());

	public Initialize() {
		// TODO Auto-generated constructor stub
	}

    /**
     * Initialize Servlet 
     * @throws ServletException from Servlet class
     */
    @Override
    public void init(ServletConfig cfg) throws ServletException {
    		super.init(cfg);
    		initializeApp(cfg.getServletContext());
    }

    /**
     * Initialize the Cache stored in the Servlet Context
     * @param context the Servlet Context
     */
    private void initializeApp(ServletContext context) {
		Cache cache;
		try {
			cache = new Cache();
		} catch (MergeException e) {
			LOGGER.log(Level.SEVERE, "Failed to initialize Cache!" + e.getMessage());
			return;
		}
		context.setAttribute("Cache", cache);
    }
    
    /**
     * No-Opp
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().write("Initialized");
    }

    /**
     * Reinitializes IDMU upon POST to this servlet
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    		initializeApp(req.getServletContext());
    		res.getWriter().write("Initialized");
    }
    
}
