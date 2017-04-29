package com.example.bubblebitoey.sw_specebook.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import com.example.bubblebitoey.sw_specebook.api.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Book implements Parcelable {
	private String id;
	private Bitmap image;
	private String title;
	private URL link;
	private double price;
	private String year;
	
	public Book(String id, String title, String link, double price, String year) {
		try {
			this.id = id;
			this.title = title;
			this.link = new URL(link);
			this.price = price;
			this.year = year;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected Book(Parcel in) {
		id = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
		title = in.readString();
		price = in.readDouble();
		year = in.readString();
	}
	
	public static final Creator<Book> CREATOR = new Creator<Book>() {
		@Override
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}
		
		@Override
		public Book[] newArray(int size) {
			return new Book[size];
		}
	};
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public URL getLink() {
		return link;
	}
	
	public void setLink(URL link) {
		this.link = link;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean haveImage() {
		return image != null;
	}
	
	public Book fetchImage() throws IOException {
		image = BitmapFactory.decodeStream((InputStream) this.link.getContent());
		return this;
	}
	
	public Bitmap getImage() throws IOException {
		return image;
	}
	
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public String getYear() {
		return year;
	}
	
	public boolean isMatch(Operation.Type type, String str) {
		switch (type) {
			case ID:
				return isMatchID(str);
			case Title:
				return isMatchTitle(str);
			case Year:
				return isMatchYear(str);
			case Price:
				return isMatchPrice(str);
		}
		return false;
	}
	
	public boolean isMatchID(String id) {
		return this.getId().startsWith(id);
	}
	
	public boolean isMatchTitle(String title) {
		String[] words = this.getTitle().split(" ");
		for (String word : words) {
			if (word.toLowerCase(Locale.ENGLISH).startsWith(title.toLowerCase(Locale.ENGLISH))) return true;
		}
		return false;
	}
	
	public boolean isMatchYear(String year) {
		return this.getYear().startsWith(year);
	}
	
	public boolean isMatchPrice(String price) {
		return String.valueOf(this.price).startsWith(price);
	}
	
	public boolean isMatchPrice(double price) {
		return isMatchPrice(String.valueOf(price));
	}
	
	@Override
	public String toString() {
		return "Book{" + "id='" + id + '\'' + ", image=" + image + ", title='" + title + '\'' + ", link=" + link + ", price=" + price + ", year='" + year + '\'' + '}';
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(id);
		parcel.writeParcelable(image, i);
		parcel.writeString(title);
		parcel.writeDouble(price);
		parcel.writeString(year);
	}
}
