package com.lordMap.datastore;

import java.util.ArrayList;
import java.util.List;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.lordMap.models.Land;
import com.lordMap.models.User;

public class DataStore {

	private DatastoreService datastore;

	public DataStore() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	//check whether userId exists
	public boolean checkUserId(String userId) {
		Key key = KeyFactory.createKey("user", "default");
		Query query = new Query(userId, key);
		List<Entity> users = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
		return !users.isEmpty();
	}
	
	//check whether password is correct
	public boolean checkUserPwd(String userId, String userPwd) {
		Key key = KeyFactory.createKey("user", "default");
		Query query = new Query(userId, key);
		List<Entity> users = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
		Entity user = users.get(0);
		String pwd = (String) user.getProperty("userPwd");
		return pwd.equals(userPwd);
	}
	
	// store user on google datastore
	public void storeUser(User newUser) {
		Key key = KeyFactory.createKey("user", "default");
		Entity user = new Entity(newUser.getUserId(), key);
		user.setProperty("userId", newUser.getUserId());
		user.setProperty("userPwd", newUser.getUserPwd());
		datastore.put(user);		
	}
	
	public void storeLand(Land newLand) {
		Key key = KeyFactory.createKey("land", "default");
		Entity land = new Entity(newLand.getOwner(), key);
		land.setProperty("userId", newLand.getOwner());
		land.setProperty("id", newLand.getId());
		land.setProperty("lat0", newLand.getLats()[0]);
		land.setProperty("lat1", newLand.getLats()[1]);
		land.setProperty("long0", newLand.getLongs()[0]);
		land.setProperty("long1", newLand.getLongs()[1]);
		land.setProperty("price", newLand.getPrice());
		land.setProperty("defence", newLand.getDefence());

		datastore.put(land);

	}
	
	public boolean checkOverlap(double[] lats1, double[] longs1, double[] lats2, double longs2) {
		return true;
	}
	
	public ArrayList<Land> showLands(String userId) {
		Key key = KeyFactory.createKey("land", "default");
		ArrayList<Land> lands = new ArrayList<Land>();
		Query query = new Query(userId, key);
		List<Entity> ls = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
		for (Entity l : ls) {
			Land nl = new Land();
			nl.setOwner(userId);
			nl.setId((Integer) l.getProperty("id"));
			nl.setPrice((Integer) l.getProperty("price"));
			nl.setDefence((Integer) l.getProperty("defence"));
			double[] lats = new double[2];
			double[] longs = new double[2];
			lats[0] = (Double) l.getProperty("lat0");
			lats[1] = (Double) l.getProperty("lat1");
			longs[0] = (Double) l.getProperty("long0");
			longs[1] = (Double) l.getProperty("long1");
			nl.setLats(lats);
			nl.setLongs(longs);
			
			lands.add(nl);
		}
		return lands;
	}
	
	public ArrayList<Land> findLands(double lat, double lng) {
		Key key = KeyFactory.createKey("land", "default");
		ArrayList<Land> lands = new ArrayList<Land>();
		Query query = new Query(key);
		List<Entity> ls = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
		// Get the results and process them, casting to the required types 
	    for (Entity ent : ls) {
	    	double[] lats = new double[5];
	    	double[] lngs = new double[5];
	    	lats[0] = lat;
	    	lngs[0] = lng;
	    	lats[1] = (Double)ent.getProperty("lat0");
	    	lngs[1] = (Double)ent.getProperty("long0");
	    	lats[2] = (Double)ent.getProperty("lat1");
	    	lngs[2] = (Double)ent.getProperty("long1");	    	
	    	if (landIsInsideCircle(lats, lngs)) {
	    		Land land = new Land();
	    		land.setLats(lats);
	    		land.setLongs(lngs);
	    		land.setOwner((String)ent.getProperty("userId"));
	    		land.setId((Integer) ent.getProperty("id"));
	    		land.setPrice((Integer) ent.getProperty("price"));
	    		land.setDefence((Integer) ent.getProperty("defence"));
	    		lands.add(land);
	    	}
	    	
	    }
	   
	    return lands;
	}
	
	private boolean landIsInsideCircle(double[] lats, double[] lngs) {
		lats[3] = lats[2];
		lngs[3] = lngs[1];
		lats[4] = lats[1];
		lngs[4] = lngs[2];
		
		for (int i = 0; i < 5; i++) {
			lats[i] += 180;
			lngs[i] += 180;
		}
		
		for (int i = 1; i < 5; i++) 
			if (pointIsInsideCircle(lats, lngs, i))
				return true;
		
		return false;
	}
	
	private boolean pointIsInsideCircle(double[] lats, double[] lngs, int index) {
		double dis = (lats[index] - lats[0]) * (lats[index] - lats[0]) + (lngs[index] - lngs[0]) * (lngs[index] - lngs[0]);    
		if (dis - RADIUS < 0.0000001)
			return true;
		
		return false;
	}
	
	private final static double RADIUS = 1;
}
