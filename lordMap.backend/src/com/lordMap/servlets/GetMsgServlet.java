package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.lordMap.datastore.DataStore;

public class GetMsgServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -871013446051409584L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject results = new JSONObject();
		JSONArray arr = new JSONArray();
		
		String userId = req.getParameter("userId");
		
		DataStore ds = new DataStore();
		ArrayList<String> msgs = ds.getMessage(userId);
		for (String msg : msgs) {
			JSONObject jo = new JSONObject();
			try {
				jo.put("msg", msg);
				arr.put(jo);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			results.put("results", arr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(results);
		out.flush();
		out.close();
	}
}
