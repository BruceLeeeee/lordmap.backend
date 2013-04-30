package com.lordMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.lordMap.datastore.DataStore;
import com.lordMap.models.Inventory;
import com.lordMap.models.Land;

public class GetPurchasedItemsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7020932736951412702L;
	private String userId;
	private boolean[] indexFlag = new boolean[Inventory.ITEM_NUM];
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		userId = req.getParameter("userId");
		for (int i = 0; i < Inventory.ITEM_NUM; i++) {
			indexFlag[i] = false;
		}
		ds.getPurchasedItems(userId, indexFlag);
		JSONObject results = new JSONObject();
		JSONArray indexArr = new JSONArray();
		for (int i = 0; i < Inventory.ITEM_NUM; i++) 
			if (indexFlag[i]) {
				JSONObject o = new JSONObject();
				try {
					o.put("index", i);
					indexArr.put(o);
				} catch (JSONException e) {
					e.printStackTrace();
				}	
			}
		
		try {
			results.put("Items", indexArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(results);
		out.flush();
		out.close();
	}
}
