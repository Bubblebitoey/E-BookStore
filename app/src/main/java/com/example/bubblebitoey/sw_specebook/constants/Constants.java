package com.example.bubblebitoey.sw_specebook.constants;

import android.net.Uri;
import com.example.bubblebitoey.sw_specebook.BuildConfig;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Constants {
	public static final URL link = getLink("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
	
	public static final String USER = "user";
	public static final String BOOK = "book";
	public static final String BOOKS = "books";
	public static final String BOOK_IMAGE = "book_image";
	
	public static final List<String> developerName = Arrays.asList("Kamontat", "Soraya");
	public static final List<Uri> developerFacebook = Arrays.asList(Uri.parse("https://www.facebook.com/kamontatc"), Uri.parse("https://www.facebook.com/soraya.sanna.1"));
	public static final String version = BuildConfig.VERSION_NAME + "b" + BuildConfig.VERSION_CODE + "";
	
	private static URL getLink(String link) {
		try {
			return new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Books getMockupBook() {
		Books books = new Books();
		books.addNewBook(new Book("1003", "name2", "https://imagery.pragprog.com/products/508/dcbang2_largebeta.jpg", 10.51, "2011"));
		books.addNewBook(new Book("1005", "name3", "https://imagery.pragprog.com/products/486/mkdsa_largebeta.jpg", 13.12, "1199"));
		books.addNewBook(new Book("1100", "name4", "https://imagery.pragprog.com/products/490/dzpyds_largecover.jpg", 15.11, "1991"));
		books.addNewBook(new Book("1011", "name5", "https://imagery.pragprog.com/products/483/jrport2_largecover.jpg", 41.15, "2015"));
		books.addNewBook(new Book("1100", "name1", "https://imagery.pragprog.com/products/504/jwdsal_largebeta.jpg", 21.11, "2001"));
		return books;
	}
}
