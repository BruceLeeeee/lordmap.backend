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
import com.lordMap.models.*;

public class GetUserInfoServlet extends HttpServlet {
	
	/**
	 * userId=? 
	 */
	private static final long serialVersionUID = -6223424561924341196L;
	private String userId;
	private ArrayList<Land> lands = new ArrayList<Land>();
	private ArrayList<String> friends = new ArrayList<String>();
	private User user;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		DataStore ds = new DataStore();
		userId = req.getParameter("userId");
		user = ds.getUserInfo(userId);
		lands = ds.showLands(userId);
		friends = ds.showFriends(userId);
		JSONObject results = new JSONObject();
		JSONObject userInfo = new JSONObject();
		JSONArray landsArr = new JSONArray();
		JSONArray friendsArr = new JSONArray();
		makeUserInfoJSONObject(userInfo);
		makeLandsJSONArray(landsArr);
		makeFriendsJSONArray(friendsArr);
		try {
			results.put("userInfo", userInfo);
			results.put("lands", landsArr);
			results.put("friends", friendsArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(results);
		out.flush();
		out.close();
	}
	
	private void makeUserInfoJSONObject(JSONObject userInfo) {
		try {
			userInfo.put("userMoney", user.getMoney());
			userInfo.put("userAtk", user.getAtk());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void makeLandsJSONArray(JSONArray landsArr) {
		for (Land l : lands) {
			JSONObject o = new JSONObject();
			try {
				o.put("id", l.getId());
				o.put("owner", l.getOwner());
				o.put("price", l.getPrice());
				o.put("defence", l.getDefence());
				o.put("lat0", l.getLats()[0]);
				o.put("long0", l.getLongs()[0]);
				o.put("lat1", l.getLats()[1]);
				o.put("long1", l.getLongs()[1]);
				landsArr.put(o);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void makeFriendsJSONArray(JSONArray friendsArr) {
		for (String f : friends) {
			JSONObject o = new JSONObject();
			try {
				o.put("friend", f);
				friendsArr.put(o);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
	}
}
