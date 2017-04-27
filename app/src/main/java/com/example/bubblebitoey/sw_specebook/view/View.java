package com.example.bubblebitoey.sw_specebook.view;

import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Store;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public interface View {
	
	void setMaxProgress(int max);
	
	void updateData(Book book);
	
	void updateData(Books books);
	
	void updateProgress(int current);
	
	void removeProgress();
	
	void search(boolean enable);
	
	void sort(Comparator<Book> compare);
	
	void filter(Store.OperationType type, String text);
	
	void login(boolean haveUser);
}
