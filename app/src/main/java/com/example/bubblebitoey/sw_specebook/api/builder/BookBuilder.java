package com.example.bubblebitoey.sw_specebook.api.builder;

import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.nullobject.NullBook;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class BookBuilder {
	public static Book createBook(String id, String title, String link, double price, String year) {
		return new Book(id, title, link, price, year);
	}
	
	public static Book createBook(JSONObject object) {
		try {
			String id = object.getString("id");
			String title = object.getString("title");
			String link = object.getString("img_url");
			double price = object.getDouble("price");
			String year = object.getString("pub_year");
			return BookBuilder.createBook(id, title, link, price, year);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new NullBook();
	}
}
