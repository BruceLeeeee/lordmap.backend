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
import com.lordMap.models.Land;

public class ShowLandServlet extends HttpServlet {

	private static final long serialVersionUID = -1311114807206287331L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String userId = req.getParameter("userId");
		DataStore ds = new DataStore();
		ArrayList<Land> lands = ds.showLands(userId);
		JSONObject results = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Land l : lands) {
			JSONObject r = new JSONObject();
			try {
				r.put("id", l.getId());
				r.put("owner", l.getOwner());
				r.put("price", l.getPrice());
				r.put("defence", l.getDefence());
				r.put("lat0", l.getLats()[0]);
				r.put("long0", l.getLongs()[0]);
				r.put("lat1", l.getLats()[1]);
				r.put("long1:", l.getLongs()[1]);
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
