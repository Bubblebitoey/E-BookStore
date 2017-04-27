package com.example.bubblebitoey.sw_specebook.model;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */
public class RealUser implements User {
	private String name;
	private int total;
	
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
}
