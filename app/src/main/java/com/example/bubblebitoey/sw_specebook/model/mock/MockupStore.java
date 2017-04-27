package com.example.bubblebitoey.sw_specebook.model.mock;

import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Store;
import com.example.bubblebitoey.sw_specebook.view.View;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public class MockupStore implements Store {
	private View view;
	
	@Override
	public Store setView(View view) {
		this.view = view;
		return this;
	}
	
	@Override
	public void loadBook() {
		view.updateData(new Book("1003", "name2", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 10.51, "2011"));
		view.updateData(new Book("1005", "name3", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 13.12, "1199"));
		view.updateData(new Book("1100", "name4", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 15.11, "1991"));
		view.updateData(new Book("1011", "name5", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 41.15, "2015"));
		view.updateData(new Book("1100", "name1", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 21.11, "2001"));
		
		view.removeProgress();
		view.search(true);
		// view.filter(OperationType.YEAR, "2001");
	}
}