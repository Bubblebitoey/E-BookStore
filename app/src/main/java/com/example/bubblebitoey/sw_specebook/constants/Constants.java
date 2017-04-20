package com.example.bubblebitoey.sw_specebook.constants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Constants {
	public static final URL link = getLink("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");

	public static URL getLink(String link) {
		try {
			return new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
