package com.example.bubblebitoey.sw_specebook.presenter;

import android.view.MenuItem;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.mock.MockupUser;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.view.UserDetailActivity;
import com.example.bubblebitoey.sw_specebook.view.View;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class MainPresenter {
	private View view;
	private Store store;
	private User user;
	
	public MainPresenter(View v, Store store) {
		this.view = v;
		this.store = store;
	}
	
	public void loadData() {
		store.loadBook();
	}
	
	public void menuClick(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.login:
				login(new MockupUser());
				view.login(user);
				break;
			case R.id.user:
				Map<String, Serializable> map = new HashMap<>();
				map.put("user", user);
				view.to(map, UserDetailActivity.class);
				break;
			case R.id.logout:
				logout();
				view.logout();
				break;
			case R.id.about:
				// show about app
				break;
			default:
				
				break;
		}
	}
	
	public void login(User user) {
		if (this.user == null) this.user = user;
	}
	
	public void logout() {
		user = null;
	}
}
