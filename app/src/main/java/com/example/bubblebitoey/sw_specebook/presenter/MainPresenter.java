package com.example.bubblebitoey.sw_specebook.presenter;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.model.Book;
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
	public static final int CALL_USER_ACTIVITY = 1000;
	private BookListView view;
	private Store store;
	private User user;
	
	public MainPresenter(BookListView v, Store store) {
		this.view = v;
		this.store = store;
		
		loadData();
		setOnClick();
	}
	
	public void loadData() {
		store.loadBook();
	}
	
	private void setOnClick() {
		view.setOnClickBook(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View newView, int i, long l) {
				Log.d("NEXT PAGE", "click image");
				Map<String, Serializable> map = new HashMap<>();
				map.put("book", (Book) adapterView.getItemAtPosition(i));
				view.to(map, null); // TODO: 4/27/2017 AD show book information page
			}
		});
	}
	
	public void menuClick(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.login:
				login(UserFactory.getInstance().createMockUser()); // TODO: 4/27/2017 AD change to non mock
				view.login(user);
				break;
			case R.id.user:
				view.toAndWait(CALL_USER_ACTIVITY, UserDetailActivity.class);
				break;
			case R.id.logout:
				logout();
				view.logout();
				break;
			case R.id.about:
				// show about app // TODO: 4/27/2017 AD about page
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
