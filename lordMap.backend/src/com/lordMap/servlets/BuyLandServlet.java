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
import com.lordMap.models.Land;

public class BuyLandServlet extends HttpServlet {

	private static final long serialVersionUID = 2295494714038913299L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		
		String userId = req.getParameter("userId");
		double lat0 = Double.valueOf(req.getParameter("lat0"));
		double lat1 = Double.valueOf(req.getParameter("lat1"));
		double long0 = Double.valueOf(req.getParameter("long0"));
		double long1 = Double.valueOf(req.getParameter("long1"));
		double[] lats = new double[2];
		double[] longs = new double[2];
		lats[0] = lat0;
		lats[1] = lat1;
		longs[0] = long0;
		longs[1] = long1;
		DataStore ds = new DataStore();
		String resultStr = null;
		if (ds.checkOverlaps(lats, longs)) {
			resultStr = "no";
		}
		else {
			resultStr = "yes";
			Land nl = new Land();
			nl.setLats(lats);
			nl.setLongs(longs);
			nl.setOwner(userId);
			ds.storeLand(nl);
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
