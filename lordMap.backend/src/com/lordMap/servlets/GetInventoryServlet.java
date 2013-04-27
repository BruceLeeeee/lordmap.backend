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
import com.lordMap.models.Land;
import com.lordMap.models.Inventory;;

public class GetInventoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject results = new JSONObject();
		JSONArray arr = new JSONArray();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			JSONObject r = new JSONObject();
			try {
				r.put("index", i + 1);
				r.put("name", Inventory.name[i]);
				r.put("price", Inventory.price[i]);
				r.put("atkPoint", Inventory.atkPoint[i]);
				r.put("defPoint", Inventory.defPoint[i]);
				arr.put(r);
			} catch (JSONException e) {
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
