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

public class GetSurroundingServlet extends HttpServlet {
	/**
	 * userId=?lat=?lng=?
	 */
	private static final long serialVersionUID = 9049311948468914720L;
	
	private String userId;
	private double lat;
	private double lng;
	private ArrayList<Land> lands = new ArrayList<Land>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		parseReq(req);
		lands = ds.findLands(lat, lng);
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
				r.put("long1", l.getLongs()[0]);
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
		
	private void parseReq(HttpServletRequest req) {
		userId = req.getParameter("userIds");
		String tmp = req.getParameter("lat");		
		lat = Double.parseDouble(tmp);
		tmp = req.getParameter("lng");		
		lng = Double.parseDouble(tmp);
	}
}
