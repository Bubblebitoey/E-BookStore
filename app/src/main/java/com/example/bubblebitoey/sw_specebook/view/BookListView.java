package com.example.bubblebitoey.sw_specebook.view;

import android.widget.AdapterView;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.model.raw.User;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public interface BookListView extends View {
	
	void setMaxProgress(int max);
	
	void updateData(Book book);
	
	void updateData(Books books);
	
	void updateProgress(int current);
	
	void removeProgress();
	
	void search(boolean enable);
	
	void sort(Comparator<Book> compare);
	
	void filter(Store.OperationType type, String text);
	
	void login(User u);
	
	void logout();
	
	void setOnClickBook(AdapterView.OnItemClickListener listener);
}
