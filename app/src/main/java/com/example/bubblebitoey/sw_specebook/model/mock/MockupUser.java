package com.example.bubblebitoey.sw_specebook.model.mock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Cart;
import com.example.bubblebitoey.sw_specebook.model.raw.User;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class MockupUser implements User {
	private String name;
	private double money;
	
	public MockupUser() {
		name = "Mockup User";
		money = 1000;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double getCurrentMoney() {
		return money;
	}
	
	@Override
	public Bitmap getImage(Context t) {
		return BitmapFactory.decodeResource(t.getResources(), R.drawable.user_picture_default);
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
		return Constants.getMockupBook();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
	}
	
	public static final Creator<MockupUser> CREATOR = new Creator<MockupUser>() {
		@Override
		public MockupUser createFromParcel(Parcel in) {
			return new MockupUser();
		}
		
		@Override
		public MockupUser[] newArray(int size) {
			return new MockupUser[size];
		}
	};
}