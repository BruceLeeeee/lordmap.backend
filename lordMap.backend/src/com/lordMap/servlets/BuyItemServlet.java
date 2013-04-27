package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lordMap.datastore.DataStore;

public class BuyItemServlet extends HttpServlet {

	
	/**
	 * buyitem?userId=?&index=?
	 */
	private static final long serialVersionUID = -868136923430771098L;
	
	private String userId;
	private int inventoryIndex;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		parseReq(req);
	}

	private void parseReq(HttpServletRequest req) {
		userId = req.getParameter("userId");
		String tmp = req.getParameter("lat");
		inventoryIndex = Integer.parseInt(tmp);
	}
}
