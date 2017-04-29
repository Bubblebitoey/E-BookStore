package com.example.bubblebitoey.sw_specebook.model.real;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;
import com.example.bubblebitoey.sw_specebook.model.raw.User;

import java.util.*;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */
public class RealUser implements User {
	private String name;
	private double total;
	
	private List<Cart> cart;
	
	public RealUser(String name) {
		this.name = name;
		total = 0;
	}
	
	public RealUser(String name, double total) {
		this.name = name;
		this.total = total;
	}
	
	public RealUser(Parcel parcel) {
		this.name = parcel.readString();
		this.total = parcel.readDouble();
		this.cart = parcel.createTypedArrayList(Cart.CREATOR);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double getCurrentMoney() {
		return total;
	}
	
	@Override
	public Bitmap getImage(Context t) {
		return BitmapFactory.decodeResource(t.getResources(), R.drawable.user_picture_default);
	}
	
	@Override
	public void pay(double amount) {
		total -= amount;
	}
	
	@Override
	public void addMoney(double money) {
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
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeDouble(total);
		parcel.writeTypedList(cart);
	}
	
	public static final Creator<RealUser> CREATOR = new Creator<RealUser>() {
		@Override
		public RealUser createFromParcel(Parcel in) {
			return new RealUser(in);
		}
		
		@Override
		public RealUser[] newArray(int size) {
			return new RealUser[size];
		}
	};
}
