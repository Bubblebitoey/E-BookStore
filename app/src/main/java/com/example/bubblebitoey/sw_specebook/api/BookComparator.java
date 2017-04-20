package com.example.bubblebitoey.sw_specebook.api;

import com.example.bubblebitoey.sw_specebook.model.Book;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class BookComparator implements Comparator<Book> {
	@Override
	public int compare(Book o1, Book o2) {
		int title = o1.getTitle().compareTo(o2.getTitle());
		if (title == 0) return o1.getYear().compareTo(o2.getYear());
		return title;
	}
}
