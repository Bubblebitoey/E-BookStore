package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.view.MainActivity;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 12:31 PM
 */
public class UserBooksPresenter extends AbstractBookListPresenter {
	protected BookListView view;
	
	@Override
	public ViewPresenter setView(BookListView view) {
		this.view = view;
		presenterSetting();
		return this;
	}
	
	@Override
	public void presenterSetting() {
		super.presenterSetting();
	}
	
	@Override
	public void login() {
		// cannot login
	}
	
	@Override
	public void logout() {
		super.logout();
		view.to(MainActivity.class);
	}
}
