package com.example.bubblebitoey.sw_specebook.model.mock;

import android.os.AsyncTask;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/26/2017 AD.
 */
public class MockupStore extends AsyncTask<Void, Void, Void> implements Store {
	private BookListPresenter presenter;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		presenter.startLoading();
	}
	
	@Override
	protected Void doInBackground(Void... voids) {
		Books books = Constants.getMockupBook();
		presenter.setBookNumber(books.size());
		
		for (Book b : books.getBooks()) {
			try {
				presenter.addBook(b.fetchImage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
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
		execute();
		// view.filter(OperationToList.Year, "2001");
	}
}
