package com.lordMap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyLandServlet extends HttpServlet {

	private static final long serialVersionUID = 2295494714038913299L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		double lat0 = Double.valueOf(req.getParameter("lat0"));
		double lat1 = Double.valueOf(req.getParameter("lat1"));
		double long0 = Double.valueOf(req.getParameter("long0"));
		double long1 = Double.valueOf(req.getParameter("long1"));
		
	}
}
