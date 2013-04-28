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

public class AttackServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846445916614253342L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		
		String userId = req.getParameter("userId");
		int landId = Integer.valueOf(req.getParameter("landId"));
		
		DataStore ds = new DataStore();
		String resultStr = ds.attack(userId, landId);
		
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

}
