package com.example.bubblebitoey.sw_specebook.view;

import android.widget.AdapterView;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public interface BookListView extends BookView {
	
	void setMaxProgress(int max);
	
	void addNewBook(Book book);
	
	void updateProgress(int current);
	
	void removeProgress();
	
	void search(boolean enable);
	
	void sort(Comparator<Book> compare);
	
	void filter(Store.OperationType type, String text);
	
	void setOnClickBook(AdapterView.OnItemClickListener listener);
}
