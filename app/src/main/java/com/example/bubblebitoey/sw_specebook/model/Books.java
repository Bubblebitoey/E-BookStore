package com.example.bubblebitoey.sw_specebook.model;

import com.android.internal.util.Predicate;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.raw.Booklist;
import com.example.bubblebitoey.sw_specebook.model.raw.Filterable;

import java.io.Serializable;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Books implements Serializable, Booklist, Filterable<Books> {
	private static Operation.FilteringBook filter;
	private long serialVersionUID = 0L;
	private List<Book> books;
	
	public Books(Book... book) {
		filter = new Operation.FilteringBook();
		this.books = new ArrayList<>(Arrays.asList(book));
	}
	
	@Override
	public void addNewBook(Book book) {
		books.add(book);
	}
	
	@Override
	public void addAll(Books books) {
		this.books.addAll(books.getBooks());
	}
	
	@Override
	public void clear() {
		this.books.clear();
	}
	
	@Override
	public Book getBook(int pos) {
		try {
			return books.get(pos);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<Book> getBooks() {
		return books.subList(0, books.size());
	}
	
	public int size() {
		return books.size();
	}
	
	public void sort(Comparator<? super Book> comparator) {
		Collections.sort(books, comparator);
	}
	
	@Override
	public void sort(Operation.Type type) {
		sort(new Operation.SortingBook().by(type));
	}
	
	@Override
	public Books filter(Operation.Type type, String str) {
		filter.update(str);
		Predicate<Book> predicate = filter.by(type);
		Books newBooks = new Books();
		for (Book b : books) {
			if (predicate.apply(b)) newBooks.addNewBook(b);
		}
		return newBooks;
	}
}
