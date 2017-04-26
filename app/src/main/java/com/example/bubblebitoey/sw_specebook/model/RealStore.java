package com.example.bubblebitoey.sw_specebook.model;

import android.os.AsyncTask;
import com.example.bubblebitoey.sw_specebook.api.Internet;
import com.example.bubblebitoey.sw_specebook.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class RealStore extends AsyncTask<Void, Void, Void> implements Store {
	private View view;

	@Override
	protected Void doInBackground(Void... params) {
		JSONArray a = Internet.fetchData();
		view.setMaxProgress(a.length());

		try {
			for (int i = 0; i < a.length(); i++) {
				JSONObject o = a.getJSONObject(i);
				Book b = new Book(o).fetchImage();
				view.updateData(b);
				view.updateProgress(i + 1);
				System.out.println("fetch (" + i + "/" + a.length() + ")");
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void voids) {
		view.removeProgress();
	}

	@Override
	public Store setView(View view) {
		this.view = view;
		return this;
	}

	@Override
	public void loadBook() {
		this.execute();
	}
}
