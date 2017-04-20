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

	public Book getBook(int pos) {
		return books.get(pos);
	}

	public List getBooks() {
		return books;
	}

	public void sort() {

	}
}
