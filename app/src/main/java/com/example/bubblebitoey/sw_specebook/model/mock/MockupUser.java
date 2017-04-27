package com.example.bubblebitoey.sw_specebook.model.mock;

import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;
import com.example.bubblebitoey.sw_specebook.model.NullBook;
import com.example.bubblebitoey.sw_specebook.model.raw.User;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class MockupUser implements User {
	private double money = 1000;
	
	@Override
	public String getName() {
		return "Mockup User";
	}
	
	@Override
	public double getCurrentMoney() {
		return money;
	}
	
	@Override
	public void pay(double amount) {
		money -= amount;
	}
	
	@Override
	public void addMoney(double money) {
		this.money += money;
	}
	
	@Override
	public void add(Cart cart) {
	}
	
	@Override
	public Books getOwnerBook() {
		return new Books(new NullBook());
	}
}