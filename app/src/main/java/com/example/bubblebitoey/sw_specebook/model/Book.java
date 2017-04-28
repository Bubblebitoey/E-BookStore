package com.example.bubblebitoey.sw_specebook.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Book implements Serializable {
	private long serialVersionUID = 0L;
	private String id;
	private transient Bitmap image;
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
	
	public boolean isMatchYear(String year) {
		return this.getYear().startsWith(year.toLowerCase(Locale.ENGLISH));
	}
	
	public boolean isMatchTitle(String title) {
		String[] words = this.getTitle().split(" ");
		for (String word : words) {
			if (word.startsWith(title.toLowerCase(Locale.ENGLISH))) return true;
		}
		return false;
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
}
