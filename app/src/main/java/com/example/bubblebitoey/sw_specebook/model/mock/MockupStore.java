package com.example.bubblebitoey.sw_specebook.model.mock;

import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.view.BookListView;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public class MockupStore implements Store {
	private BookListView view;
	
	@Override
	public Store setView(BookListView view) {
		this.view = view;
		return this;
	}
	
	@Override
	public void loadBook() {
		view.addNewBook(new Book("1003", "name2", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 10.51, "2011"));
		view.addNewBook(new Book("1005", "name3", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 13.12, "1199"));
		view.addNewBook(new Book("1100", "name4", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 15.11, "1991"));
		view.addNewBook(new Book("1011", "name5", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 41.15, "2015"));
		view.addNewBook(new Book("1100", "name1", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 21.11, "2001"));
		
		view.removeProgress();
		view.search(true);
		// view.filter(OperationToList.YEAR, "2001");
	}
}
