package com.example.bubblebitoey.sw_specebook.presenter;

import android.view.MenuItem;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.view.BookListView;
import com.example.bubblebitoey.sw_specebook.view.UserDetailActivity;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class MainPresenter {
	private BookListView view;
	private Store store;
	private User user;
	
	public MainPresenter(BookListView v, Store store) {
		this.view = v;
		this.store = store;
		
		loadData();
	}
	
	public void loadData() {
		store.loadBook();
	}
	
	public void menuClick(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.login:
				login(UserFactory.getInstance().createMockUser()); // TODO: 4/27/2017 AD change to non mock
				view.login(user);
				break;
			case R.id.user:
				Map<String, Serializable> map = new HashMap<>();
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
