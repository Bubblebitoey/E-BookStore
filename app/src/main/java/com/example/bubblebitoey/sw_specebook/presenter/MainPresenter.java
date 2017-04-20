package com.example.bubblebitoey.sw_specebook.presenter;

import android.os.AsyncTask;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.view.View;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class MainPresenter extends AsyncTask<Book, Void, Books> {
	View view;

	public MainPresenter(View v) {
		view = v;
	}

	@Override
	protected Books doInBackground(Book... params) {
		try {
			for (Book b : params) {
				b.fetchImage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Books(params);
	}

	@Override
	protected void onPostExecute(Books books) {
		view.setData(books);
	}
}
