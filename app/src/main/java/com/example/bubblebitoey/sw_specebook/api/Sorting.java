package com.example.bubblebitoey.sw_specebook.api;

import com.example.bubblebitoey.sw_specebook.model.Book;

import java.util.*;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public abstract class Sorting implements Comparator<Book> {
	public static class ByTitle extends Sorting {
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	}
	
	public static class ByYear extends Sorting {
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getYear().compareTo(o2.getYear());
		}
	}
	
	public static class ByPrice extends Sorting {
		@Override
		public int compare(Book o1, Book o2) {
			return Double.compare(o1.getPrice(), o2.getPrice());
		}
	}
}
