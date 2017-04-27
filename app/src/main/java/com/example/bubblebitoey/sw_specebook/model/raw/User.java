package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;

import java.io.Serializable;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public interface User extends Serializable {
	String getName();
	
	double getCurrentMoney();
	
	void pay(double amount);
	
	void addMoney(double money);
	
	void add(Cart cart);
	
	Books getOwnerBook();
}
