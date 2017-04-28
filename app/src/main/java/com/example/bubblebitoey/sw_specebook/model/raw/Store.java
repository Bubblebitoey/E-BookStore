package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public interface Store {
	
	Store setView(BookListView view);
	
	void loadBook();
}
