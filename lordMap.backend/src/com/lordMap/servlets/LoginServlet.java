package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lordMap.datastore.DataStore;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6947962980934714944L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
//		out.println("hello!");
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		out.println(userId + userPwd);
		DataStore ds = new DataStore();
//		//if id exists
		if (!ds.checkUserId(userId)) {
			out.println("Not in datastore");
		}
//			if (ds.checkUserPwd(userId, userPwd)) {	
//				resp.setStatus(1);
//			}
//			//pwd is not correct
//			else {
//				resp.setStatus(-2);
//			}
//		}
//		else {
//			resp.setStatus(-1);
//		}
		
		
	}

}
