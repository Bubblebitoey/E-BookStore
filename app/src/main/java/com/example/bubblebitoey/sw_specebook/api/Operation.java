package com.example.bubblebitoey.sw_specebook.api;

import com.android.internal.util.Predicate;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.nullobject.NullComparator;

import java.util.*;

/**
 * Created by kamontat on 4/28/2017 AD.
 */

public interface Operation<R> {
	enum Type {
		ID, Title, Year, Price;
	}
	
	R by(Type type);
	
	void update(String str);
	
	
	public class SortingBook implements Operation<Comparator<Book>> {
		@Override
		public Comparator<Book> by(Type type) {
			switch (type) {
				case ID:
					return new Sorting.ByID();
				case Title:
					return new Sorting.ByTitle();
				case Year:
					return new Sorting.ByYear();
				case Price:
					return new Sorting.ByPrice();
			}
			return new NullComparator<>();
		}
		
		@Override
		public void update(String str) {
		}
	}
	
	abstract class Sorting implements Comparator<Book> {
		public static class ByID extends Sorting {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getId().compareTo(o2.getId());
			}
		}
		
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
	
	class FilteringBook implements Operation<Predicate<Book>> {
		private String text;
		
		@Override
		public Predicate<Book> by(final Type type) {
			return new Predicate<Book>() {
				@Override
				public boolean apply(Book book) {
					return book.isMatch(type, text);
				}
			};
		}
		
		@Override
		public void update(String str) {
			text = str;
		}
	}
}