package com.example.bubblebitoey.sw_specebook.api.factory;

import com.example.bubblebitoey.sw_specebook.model.mock.MockupUser;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.model.real.RealUser;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class UserFactory {
	private static UserFactory factory;
	
	private User user;
	
	public static UserFactory getInstance() {
		if (factory == null) factory = new UserFactory();
		return factory;
	}
	
	public User createMockUser() {
		if (user == null) user = new MockupUser();
		return user;
	}
	
	public User createRealUser(String name) {
		if (user == null) user = new RealUser(name);
		return user;
	}
	
	// create first
	public User getUser() {
		return user;
	}
}
