package com.example.bubblebitoey.sw_specebook.presenter;

import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 10:30 AM
 */
public interface BookListPresenter extends ViewPresenter<BookListView> {
	void addBook(Book b);
	
	void addBook(Book b, int number);
	
	void addBooks(Books b);
	
	void setBookNumber(int number);
	
	void startLoading();
	
	void endLoading();
	
	void sort(Operation.Type type);
	
	void filter(Operation.Type type, String str);
	
	void notifyWhenDataSetChange();
	
	void fetchBook();
	
	void clear();
}
