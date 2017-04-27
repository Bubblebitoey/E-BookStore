package com.example.bubblebitoey.sw_specebook.model.raw;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;

import java.io.Serializable;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public interface User extends Serializable {
	String DEFAULT_IMAGE_NAME = "user_picture_default.png";
	
	String getName();
	
	double getCurrentMoney();
	
	Bitmap getImage(Context t);
	
	void pay(double amount);
	
	void addMoney(double money);
	
	void add(Cart cart);
	
	Books getOwnerBook();
}
