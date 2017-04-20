package com.example.bubblebitoey.sw_specebook.presenter;

import android.os.AsyncTask;
import com.example.bubblebitoey.sw_specebook.api.Internet;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class MainPresenter extends AsyncTask<Void, Void, Books> {
	private View view;

	public MainPresenter(View v) {
		view = v;
	}

	@Override
	protected Books doInBackground(Void... params) {
		Books books = new Books();
		JSONArray a = Internet.fetchData();
		view.setMaxProgress(a.length());

		try {
			for (int i = 0; i < a.length(); i++) {
				JSONObject o = a.getJSONObject(i);
				Book b = new Book(o).fetchImage();
				books.add(b);
				view.updateData(b);
				view.updateProgress(i + 1);
				System.out.println("fetch (" + i + "/" + a.length() + ")");
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	protected void onPostExecute(Books books) {
		view.removeProgress();
	}
}
