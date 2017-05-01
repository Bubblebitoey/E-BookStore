package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.presenter.UserBooksPresenter;

public class UserBookListActivity extends BookListActivity {
	
	public UserBookListActivity() {
		super(new UserBooksPresenter());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		User user = (User) getIntent().getParcelableExtra("user");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void login() {
		super.login();
	}
	
	@Override
	public void logout() {
		super.logout();
		to(MainActivity.class);
	}
}