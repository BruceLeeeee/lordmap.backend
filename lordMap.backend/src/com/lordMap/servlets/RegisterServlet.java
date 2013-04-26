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
import com.lordMap.models.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884530350817480376L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();

		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		DataStore ds = new DataStore();
		//if userId already exists
		if (ds.checkUserId(userId)) {
			try {
				result.put("result", "no");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				result.put("result", "yes");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user = new User();
			user.setUserId(userId);
			user.setUserPwd(userPwd);
			ds.storeUser(user);
		}
		out.print(result);
		out.flush();
		out.close();
	}
}
