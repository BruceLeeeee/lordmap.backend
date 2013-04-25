package com.lordMap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String userId =req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		DataStore ds = new DataStore();
		//if userId already exists
		if (ds.checkUserId(userId)) {
			resp.setStatus(-1);
		}
		else {
			User user = new User();
			user.setUserId(userId);
			user.setUserPwd(userPwd);
			ds.storeUser(user);
			resp.setStatus(1);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
