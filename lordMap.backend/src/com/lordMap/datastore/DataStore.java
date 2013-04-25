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

	//check whether it's searched before
//	public boolean inDataStore() {
//		Query query = new Query(category, tweetKey);
//		List<Entity> tweets = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
//		return !tweets.isEmpty();
//	}

	//get tweets from datastore
//	public List<Tweet> retrieveTweets() {
//		Query query = new Query(category, tweetKey);
//		List<Entity> tweets = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
//		List<Tweet> tList = new ArrayList<Tweet>();
//		for (Entity t : tweets) {
//			Tweet newTweet = new Tweet();
//			newTweet.setFrom_user((String) t.getProperty("from_user"));
//			newTweet.setFrom_user_name((String) t.getProperty("from_user_name"));
//			newTweet.setProfile_image_url((String) t.getProperty("profile_image_url"));
//			newTweet.setText((String) t.getProperty("text"));
//			tList.add(newTweet);
//		}
//		return tList;
//	}

}
