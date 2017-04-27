package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.view.BookListView;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public interface Store {
	enum OperationType {
		TITLE, YEAR
	}

	Store setView(BookListView view);

	void loadBook();
}
