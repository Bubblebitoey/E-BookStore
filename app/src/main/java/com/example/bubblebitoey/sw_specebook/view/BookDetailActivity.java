package com.example.bubblebitoey.sw_specebook.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.view.PassingActivity;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.presenter.BookDetailPresenter;
import com.example.bubblebitoey.sw_specebook.view.raw.BookView;

import java.io.IOException;
import java.util.*;

public class BookDetailActivity extends TopMenuActivity<BookView> implements BookView {
	private Book book;
	
	public BookDetailActivity() {
		super(new BookDetailPresenter());
		getPresenter().setView(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
		Book b = getIntent().getParcelableExtra("book");
		new FetchImage().execute();
		
		if (getActionBar() != null) getActionBar().setTitle(b.getTitle());
		else if (getSupportActionBar() != null) getSupportActionBar().setTitle(b.getTitle());
	}
	
	@Override
	public void to(Class nextActivity) {
		PassingActivity.newActivity(this, nextActivity);
	}
	
	@Override
	public void to(Map<String, Parcelable> data, Class nextActivity) {
		PassingActivity.newActivity(data, this, nextActivity);
	}
	
	@Override
	public void toAndWait(int code, Class nextActivity) {
		PassingActivity.newActivityWithResult(code, this, nextActivity);
	}
	
	@Override
	public void toAndWait(int code, Map<String, Parcelable> data, Class nextActivity) {
		PassingActivity.newActivityWithResult(code, data, this, nextActivity);
	}
	
	@Override
	public Context getContext() {
		return this;
	}
	
	@Override
	public void setBook(Book book) {
		this.book = book;
	}
	
	private class FetchImage extends AsyncTask<Book, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(Book... books) {
			try {
				if (books.length > 0) return books[0].fetchImage().getImage();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			if (bitmap != null) setImage(bitmap);
		}
	}
	
	private void setImage(Bitmap bitmap) {
		
	}
}
