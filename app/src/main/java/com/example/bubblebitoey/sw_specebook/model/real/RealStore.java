package com.example.bubblebitoey.sw_specebook.model.real;

import android.os.AsyncTask;
import android.util.Log;
import com.example.bubblebitoey.sw_specebook.api.Internet;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.api.builder.BookBuilder;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class RealStore extends AsyncTask<Void, Void, Void> implements Store {
	private BookListPresenter presenter;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		presenter.startLoading();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		JSONArray a = Internet.fetchData();
		presenter.setBookNumber(a.length());
		try {
			for (int i = 0; i < a.length(); i++) {
				JSONObject o = a.getJSONObject(i);
				Book b = BookBuilder.createBook(o).fetchImage();
				presenter.addBook(b, i + 1);
				Log.i("FETCH BOOKS", "(" + (i + 1) + "/" + a.length() + ")");
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void voids) {
		presenter.sort(Operation.Type.Title);
		presenter.endLoading();
	}
	
	@Override
	public Store setPresenter(BookListPresenter presenter) {
		this.presenter = presenter;
		return this;
	}
	
	@Override
	public void loadBook() {
		this.execute();
	}
}
