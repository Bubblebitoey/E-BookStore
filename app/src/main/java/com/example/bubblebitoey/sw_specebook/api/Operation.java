package com.example.bubblebitoey.sw_specebook.api;

import com.android.internal.util.Predicate;
import com.example.bubblebitoey.sw_specebook.model.Book;

import java.util.*;

/**
 * Created by kamontat on 4/28/2017 AD.
 */

public class Operation {
	
	public enum Type {
		TITLE, YEAR;
	}
	
	public static class SortingBook extends Operation implements Sort<Book> {
		public static SortingBook GET = new SortingBook();
		
		private SortingBook() {
		}
		
		@Override
		public Comparator<Book> by(Type type) {
			switch (type) {
				case TITLE:
					return new com.example.bubblebitoey.sw_specebook.api.Sorting.ByTitle();
				case YEAR:
					return new com.example.bubblebitoey.sw_specebook.api.Sorting.ByYear();
			}
			return new NullComparator<>();
		}
	}
	
	public static class FilteringBook extends Operation implements Filter<Book> {
		private String text;
		
		public FilteringBook(String text) {
			this.text = text;
		}
		
		public void updateText(String text) {
			this.text = text;
		}
		
		@Override
		public Predicate<Book> by(Type type) {
			switch (type) {
				case TITLE:
					return new Filtering.ByTitle(text);
				case YEAR:
					return new Filtering.ByYear(text);
			}
			return new NullPredicate<>();
		}
	}
}

// main interface
interface Sort<T> {
	Comparator<T> by(Operation.Type type);
}

// help class
abstract class Sorting implements Comparator<Book> {
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

// default comparator
class NullComparator<T> implements Comparator<T> {
	@Override
	public int compare(Object o, Object t1) {
		return 0;
	}
}

/* -------------- Filter ----------------- */

interface Filter<T> {
	Predicate<T> by(Operation.Type type);
}

abstract class Filtering implements Predicate<Book> {
	private String text;
	
	public Filtering(String text) {
		this.text = text;
	}
	
	public static class ByTitle extends Filtering {
		public ByTitle(String text) {
			super(text);
		}
		
		@Override
		public boolean apply(Book book) {
			return book.isMatchTitle(super.text);
		}
	}
	
	public static class ByYear extends Filtering {
		public ByYear(String text) {
			super(text);
		}
		
		@Override
		public boolean apply(Book book) {
			return book.isMatchYear(super.text);
		}
	}
	
	public static class ByPrice extends Filtering {
		public ByPrice(String text) {
			super(text);
		}
		
		@Override
		public boolean apply(Book book) {
			return book.isMatchPrice(super.text);
		}
	}
}

class NullPredicate<T> implements Predicate<T> {
	@Override
	public boolean apply(T t) {
		return true;
	}
}