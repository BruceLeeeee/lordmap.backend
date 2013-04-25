package com.lordMap.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lordMap.datastore.DataStore;
import com.lordMap.models.Land;

public class ShowLandServlet extends HttpServlet {

	private static final long serialVersionUID = -1311114807206287331L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		String userId = req.getParameter("userId");
		DataStore ds = new DataStore();
		ArrayList<Land> lands = ds.showLands(userId);
		
	}
}
