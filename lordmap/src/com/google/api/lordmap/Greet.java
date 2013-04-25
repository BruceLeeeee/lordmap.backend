package com.google.api.lordmap;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;

@Api(
    name = "Lordmap",
    version = "v1"
)
public class Greet {
	@ApiMethod(name = "users.login")
	public String login(User user) throws IOException {
		insert(user);
		return "Greeting, " + user.getName(); 
	}
	
	public User insert(User user) throws IOException {
		PersistenceManager pm = getPersistenceManager();
		pm.makePersistent(user);
		pm.close();
		return user;
	}

	private static PersistenceManager getPersistenceManager() {
	    return PMF.get().getPersistenceManager();
	}
}
