package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.lordMap.datastore.DataStore;

public class StartTaskServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1507144371056636144L;
	private String userId;
	private int taskId; 
	private double lat;
	private double lng;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		JSONObject result = new JSONObject();
		String resultStr = null;
		parseReq(req);
		switch (taskId) {
		case 1:
			if (ds.storeTask1Info(userId, lat, lng))
				resultStr = "1";
			else 
				resultStr = "2";
			break;
		case 2:
		}
		
		try {
			result.put("result", resultStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(result);
		out.flush();
		out.close();
	}

	private void parseReq(HttpServletRequest req) {
		userId = req.getParameter("userId");
		String tmp = req.getParameter("lat");		
		lat = Double.parseDouble(tmp);
		tmp = req.getParameter("lng");		
		lng = Double.parseDouble(tmp);
		tmp = req.getParameter("taskId");		
		taskId = Integer.parseInt(tmp);
	}
}
