package com.example.bubblebitoey.sw_specebook.model;

import java.io.Serializable;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public interface User extends Serializable {
	String getName();
	
	int getCurrentMoney();
	
	void pay(int amount);
	
	void addMoney(int money);
	
	void add(Cart cart);
	
	Books getOwnerBook();
}
