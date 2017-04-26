package com.example.bubblebitoey.sw_specebook.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Book {
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

	public Book(JSONObject object) throws JSONException {
		this(object.getString("id"), object.getString("title"), object.getString("img_url"), object.getDouble("price"), object.getString("pub_year"));
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

	public boolean isSameYear(Book b) {
		return b.getYear().equals(this.getYear());
	}

	public boolean isSameTitle(Book b) {
		return b.getTitle().equals(this.getTitle());
	}

	@Override
	public String toString() {
		return "Book{" +
				       "id='" + id + '\'' +
				       ", image=" + image +
				       ", title='" + title + '\'' +
				       ", link=" + link +
				       ", price=" + price +
				       ", year='" + year + '\'' +
				       '}';
	}
}
