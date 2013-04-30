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

public class BuyItemServlet extends HttpServlet {

	
	/**
	 * buyitem?userId=?&index=?
	 * yes/no
	 */
	private static final long serialVersionUID = -868136923430771098L;
	
	private String userId;
	private int inventoryIndex;
	private long balance;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		JSONObject result = new JSONObject();
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		parseReq(req);
		String resultStr = null;
		if (ds.isAffordable(userId, inventoryIndex)) {
			balance = ds.purchaseItem(userId, inventoryIndex);
			ds.equipItem(userId, inventoryIndex);
			resultStr = "yes";
			
		} else {
			resultStr = "no";
		}
		
		try {
			result.put("result", resultStr);
			result.put("balance", balance);
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
		String tmp = req.getParameter("index");
		inventoryIndex = Integer.parseInt(tmp) - 1;
	}
}
