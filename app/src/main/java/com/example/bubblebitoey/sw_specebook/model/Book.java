package com.example.bubblebitoey.sw_specebook.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Book {
	private int id;
	private Bitmap image;
	private String title;
	private URL link;
	private double price;

	public Book(int id, String title, String link, double price) {
		try {
			this.id = id;
			this.title = title;
			this.link = new URL(link);
			this.price = price;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void fetchImage() throws IOException {
		image = BitmapFactory.decodeStream((InputStream) this.link.getContent());
	}

	public Bitmap getImage() throws IOException {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
