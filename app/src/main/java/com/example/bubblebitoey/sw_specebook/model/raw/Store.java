package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public interface Store {
	
	Store setPresenter(BookListPresenter presenter);
	
	void loadBook();
}
