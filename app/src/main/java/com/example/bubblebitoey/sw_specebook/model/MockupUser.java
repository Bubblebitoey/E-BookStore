package com.example.bubblebitoey.sw_specebook.model;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class MockupUser implements User {
	@Override
	public String getName() {
		return "Mockup User";
	}
	
	@Override
	public int getCurrentMoney() {
		return 1000;
	}
	
	@Override
	public void pay(int amount) {
	}
	
	@Override
	public void addMoney(int money) {
	}
	
	@Override
	public void add(Cart cart) {
	}
	
	@Override
	public Books getOwnerBook() {
		return null;
	}
}