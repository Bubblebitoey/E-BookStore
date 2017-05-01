package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.view.raw.View;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 01/May/2017 - 10:20 PM
 */
public abstract class AbstractViewPresenter<T extends View> implements ViewPresenter<T> {
	protected T view;
	
	@Override
	public ViewPresenter setView(T view) {
		this.view = view;
		presenterSetting();
		return this;
	}
	
	@Override
	public void login() {
		UserFactory.getInstance().createMockUser();
	}
	
	@Override
	public void logout() {
		UserFactory.getInstance().logout();
	}
}
