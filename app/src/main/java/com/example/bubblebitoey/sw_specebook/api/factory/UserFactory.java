package com.example.bubblebitoey.sw_specebook.api.factory;

import android.support.annotation.Nullable;
import com.example.bubblebitoey.sw_specebook.model.mock.MockupUser;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.model.real.RealUser;
import com.example.bubblebitoey.sw_specebook.view.raw.View;

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
	
	public void createMockUser() {
		if (user == null) user = new MockupUser();
	}
	
	public void createRealUser(String name) {
		if (user == null) user = new RealUser(name);
	}
	
	// create first
	@Nullable
	public User getUser() {
		return user;
	}
	
	public void toggleView(View view) {
		if (UserFactory.getInstance().getUser() != null) view.login();
		else view.logout();
	}
	
	public void logout() {
		user = null;
	}
}
