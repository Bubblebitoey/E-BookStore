package com.example.bubblebitoey.sw_specebook.view;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.Book;

import java.io.IOException;

public class BookDetailActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
		Book b = (Book) getIntent().getSerializableExtra("book");
		new FetchImage().execute();
		
		if (getActionBar() != null) getActionBar().setTitle(b.getTitle());
		else if (getSupportActionBar() != null) getSupportActionBar().setTitle(b.getTitle());
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
