package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import android.util.Log;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.presenter.UserBooksPresenter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestActivity extends BookListActivity {
	
	public TestActivity() {
		super(new UserBooksPresenter());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		User user = (User) getIntent().getParcelableExtra("user");
		super.onCreate(savedInstanceState);
		Log.d("NEW PAGE WITH ", user.getClass().getName());
		
		Executor executor = Executors.newSingleThreadExecutor();
		
		try {
			Books books = user.getOwnerBook().executeOnExecutor(executor, getPresenter()).get();
			addAll(books);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
