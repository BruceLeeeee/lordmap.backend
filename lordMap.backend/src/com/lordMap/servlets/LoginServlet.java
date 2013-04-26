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

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6947962980934714944L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
//		out.println("hello!");
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
//		out.println(userId + userPwd);
		DataStore ds = new DataStore();
		
		JSONObject result = new JSONObject();

		//if id exists
		if (ds.checkUserId(userId) && ds.checkUserPwd(userId, userPwd)) {
//			out.println("Not in datastore");
			try {
				result.put("result", "yes");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				result.put("result", "no");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.print(result);
		out.flush();
		out.close();
	}

}
