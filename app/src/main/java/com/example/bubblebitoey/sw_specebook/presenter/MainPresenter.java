package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * Created by kamontat on 4/27/2017 AD.
 */
public class MainPresenter extends AbstractBookListPresenter {
	public static final int CALL_USER_ACTIVITY = 1000;
	private Store store;
	
	public MainPresenter(Store store) {
		this.store = store;
		store.setPresenter(this);
	}
	
	@Override
	public void fetchBook() {
		store.loadBook();
	}
	
	@Override
	public ViewPresenter setView(BookListView view) {
		ViewPresenter v = super.setView(view);
		fetchBook();
		return v;
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
