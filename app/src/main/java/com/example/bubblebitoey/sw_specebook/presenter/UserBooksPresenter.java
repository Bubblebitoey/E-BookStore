package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 12:31 PM
 */
public class UserBooksPresenter extends AbstractBookListPresenter {
	
	@Override
	public ViewPresenter setView(BookListView view) {
		super.setView(view);
		fetchBook();
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
		UserFactory.getInstance().logout();
		super.logout();
	}
	
	@Override
	public void fetchBook() {
		view.createProgress();
		User user = UserFactory.getInstance().getUser();
		if (user != null) {
			view.addAll(user.getOwnerBook());
		}
		view.removeProgress();
	}
}
