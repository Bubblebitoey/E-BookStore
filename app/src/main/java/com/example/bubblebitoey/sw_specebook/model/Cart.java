package com.example.bubblebitoey.sw_specebook.model;

import com.example.bubblebitoey.sw_specebook.model.raw.User;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class Cart {
	private Books books;
	private boolean isPaid;
	private int totalPrice;
	
	public Cart() {
		books = new Books();
		isPaid = false;
		totalPrice = 0;
	}
	
	public void addBook(Book book) {
		books.addNewBook(book);
		totalPrice += book.getPrice();
	}
	
	public boolean checkout(User s) {
		if (s.getCurrentMoney() >= totalPrice) {
			s.pay(totalPrice);
			s.add(this);
			isPaid = true;
		}
		return false;
	}
	
	public boolean isPaid() {
		return isPaid;
	}
	
	public Books getBooks() {
		return books;
	}
}
