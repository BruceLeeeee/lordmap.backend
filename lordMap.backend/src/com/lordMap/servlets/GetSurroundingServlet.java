package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lordMap.datastore.DataStore;
import com.lordMap.models.Land;

public class GetSurroundingServlet extends HttpServlet {
	/**
	 * userId=?lat=?lng=?
	 */
	private static final long serialVersionUID = 9049311948468914720L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		parseReq(req);
		landlist = ds.findLands(lat, lng);
	}
		
	private void parseReq(HttpServletRequest req) {
		userId = req.getParameter("userIds");
		String tmp = req.getParameter("lat");		
		lat = Integer.parseInt(tmp);
		tmp = req.getParameter("lng");		
		lng = Integer.parseInt(tmp);
	}
	private String userId;
	private int lat;
	private int lng;
	private ArrayList<Land> landlist = new ArrayList<Land>();
}
