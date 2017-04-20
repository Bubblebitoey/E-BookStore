package com.example.bubblebitoey.sw_specebook.view;

import com.example.bubblebitoey.sw_specebook.model.Book;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public interface View {
	String byTitle = "title";
	String byYear = "year";

	void setMaxProgress(int max);

	void updateData(Book book);

	void updateProgress(int current);

	void removeProgress();

	void search(boolean enable);

	void sort(Comparator<Book> compare);

	void filter(String by);
}
