package com.example.bubblebitoey.sw_specebook.view.raw;

import android.widget.AdapterView;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.raw.Booklist;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */
public interface BookListView extends BookView, Booklist, SearchView {
	
	void notifyOnChange();
	
	void setMaxProgress(int max);
	
	void updateProgress(int current);
	
	void createProgress();
	
	void removeProgress();
	
	void sort(Operation.Type type);
	
	void sort(Comparator<Book> comparator);
	
	void filter(Operation.Type type, String text);
	
	void setOnClickBook(AdapterView.OnItemClickListener listener);
}
