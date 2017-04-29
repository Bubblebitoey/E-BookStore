package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.view.raw.View;

/**
 * Created by kamontat on 4/28/2017 AD.
 */
public interface ViewPresenter<T extends View> {
	ViewPresenter setView(T view);
	
	void presenterSetting();
	
	void login();
	
	void logout();
}
