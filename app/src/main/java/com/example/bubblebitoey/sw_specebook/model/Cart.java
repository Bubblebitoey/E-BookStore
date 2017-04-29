package com.example.bubblebitoey.sw_specebook.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.example.bubblebitoey.sw_specebook.model.raw.User;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class Cart implements Parcelable {
	private Books books;
	private boolean isPaid;
	private int totalPrice;
	
	public Cart() {
		books = new Books();
		isPaid = false;
		totalPrice = 0;
	}
	
	protected Cart(Parcel in) {
		books = in.readParcelable(Books.class.getClassLoader());
		isPaid = in.readByte() != 0;
		totalPrice = in.readInt();
	}
	
	public static final Creator<Cart> CREATOR = new Creator<Cart>() {
		@Override
		public Cart createFromParcel(Parcel in) {
			return new Cart(in);
		}
		
		@Override
		public Cart[] newArray(int size) {
			return new Cart[size];
		}
	};
	
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
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeParcelable(books, i);
		parcel.writeByte((byte) (isPaid ? 1: 0));
		parcel.writeInt(totalPrice);
	}
}
