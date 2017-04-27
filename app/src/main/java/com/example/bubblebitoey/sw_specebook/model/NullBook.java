package com.example.bubblebitoey.sw_specebook.model;

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
	public boolean isSameYear(String year) {
		return false;
	}
	
	@Override
	public boolean isSameTitle(String title) {
		return false;
	}
}
