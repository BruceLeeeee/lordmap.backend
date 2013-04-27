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

public class AddFriendServlet extends HttpServlet {

	private static final long serialVersionUID = 5170052696386916135L;

	/**
	 * userId1=?userId2=?
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		
		String userId1 = req.getParameter("userId1");
		String userId2 = req.getParameter("userId2");
		
		DataStore ds = new DataStore();
		String resultStr = null;
		if  (ds.checkRequest(userId1, userId2))
			resultStr = "no";
		else {
			resultStr = "yes";
			ds.addRequest(userId1, userId2);			
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
}
