package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;

/**
 * Created by kamontat on 4/28/2017 AD.
 */

public interface BookList {
	void addNewBook(Book book);
	
	void addAll(Books books);
	
	void clear();
	
	Book getBook(int pos);
	
	void sort(Operation.Type type);
	
	void manualFilter();
}
