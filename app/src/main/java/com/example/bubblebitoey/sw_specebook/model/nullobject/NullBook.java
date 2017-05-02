package com.example.bubblebitoey.sw_specebook.model.nullobject;

import com.example.bubblebitoey.sw_specebook.model.Book;

import java.io.IOException;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class NullBook extends Book {
	public NullBook() {
		super("-1", "", "", 0.00, "");
	}
	
	@Override
	public Book fetchImage() throws IOException {
		return this;
	}
	
	@Override
	public boolean isMatchYear(String year) {
		return false;
	}
	
	@Override
	public boolean isMatchTitle(String title) {
		return false;
	}
}
