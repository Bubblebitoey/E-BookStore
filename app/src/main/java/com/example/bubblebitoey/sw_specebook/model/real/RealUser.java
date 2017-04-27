package com.example.bubblebitoey.sw_specebook.model.real;

import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;
import com.example.bubblebitoey.sw_specebook.model.User;

import java.util.*;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */
public class RealUser implements User {
	private String name;
	private int total;
	
	private List<Cart> cart;
	
	public RealUser(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getCurrentMoney() {
		return total;
	}
	
	@Override
	public void pay(int amount) {
		total -= amount;
	}
	
	@Override
	public void addMoney(int money) {
		total += money;
	}
	
	@Override
	public void add(Cart cart) {
		this.cart.add(cart);
	}
	
	@Override
	public Books getOwnerBook() {
		Books b = new Books();
		for (Cart c : cart) {
			if (c.isPaid()) b.addAll(c.getBooks());
		}
		return b;
	}
}
