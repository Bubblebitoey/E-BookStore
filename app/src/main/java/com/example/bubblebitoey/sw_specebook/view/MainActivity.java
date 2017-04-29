package com.example.bubblebitoey.sw_specebook.view;

import com.example.bubblebitoey.sw_specebook.model.real.RealStore;
import com.example.bubblebitoey.sw_specebook.presenter.MainPresenter;

public class MainActivity extends BookListActivity {
	
	public MainActivity() {
		super(new MainPresenter(new RealStore()));
	}
}