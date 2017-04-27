package com.example.bubblebitoey.sw_specebook.model;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Books {
	private List<Book> books;
	
	public Books(Book... book) {
		this.books = new ArrayList<>(Arrays.asList(book));
	}
	
	public void add(Book book) {
		books.add(book);
	}
	
	public void addAll(Books books) {
		this.books.addAll(books.getBooks());
	}
	
	public Book getBook(int pos) {
		try {
			return books.get(pos);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public int size() {
		return books.size();
	}
	
	public void sort(Comparator<? super Book> compare) {
		Collections.sort(books, compare);
	}
	
	public Books filter(Store.OperationType type, String str) {
		Books newBooks = new Books();
		switch (type) {
			case TITLE:
				for (Book b : books) {
					if (b.isSameTitle(str)) newBooks.add(b);
				}
				return newBooks;
			case YEAR:
				for (Book b : books) {
					if (b.isSameYear(str)) newBooks.add(b);
				}
				return newBooks;
		}
		return new Books();
	}
}
