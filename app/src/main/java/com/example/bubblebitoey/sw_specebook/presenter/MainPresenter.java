package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * Created by kamontat on 4/27/2017 AD.
 */
public class MainPresenter extends AbstractBookListPresenter {
	public static final int CALL_USER_ACTIVITY = 1000;
	private BookListView view;
	private Store store;
	
	public MainPresenter(Store store) {
		this.store = store;
		store.setPresenter(this);
	}
	
	public void loadData() {
		store.loadBook();
	}
	
	@Override
	public ViewPresenter setView(BookListView view) {
		this.view = view;
		presenterSetting();
		return this;
	}
	
	@Override
	public void presenterSetting() {
		loadData();
		super.presenterSetting();
	}
	
	@Override
	public void login() {
		super.login();
	}
	
	@Override
	public void logout() {
		super.logout();
	}
}
